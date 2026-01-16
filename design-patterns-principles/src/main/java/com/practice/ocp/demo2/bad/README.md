# 題目：真實場景（銀行「客戶關懷事件」觸發器）

### 不好的示範（違反 OCP）

把所有觸發條件塞在一個方法裡，新增一個觸發原因就要改引擎、改 if/else、越改越亂。

```java
import java.util.*;

class ClientSnapshot {
    String clientId;
    double loanBalance;        // 放款餘額
    double creditLimit;        // 授信額度
    double depositBalance;     // 存款餘額
    double bondPnLPercent;     // 債券浮動損益(%)，例如 -8.5 表示 -8.5%
    boolean overdue;           // 是否逾期
}

class CareEvent {
    String clientId;
    String reasonCode;
    String message;

    CareEvent(String clientId, String reasonCode, String message) {
        this.clientId = clientId;
        this.reasonCode = reasonCode;
        this.message = message;
    }
}

class CareEventEngine_Bad {

    public List<CareEvent> detect(ClientSnapshot s) {
        List<CareEvent> events = new ArrayList<>();

        // 規則 1：債券損益過大
        if (s.bondPnLPercent <= -8.0) {
            events.add(new CareEvent(s.clientId, "BOND_PNL_DROP", "債券損益下跌超過門檻"));
        }

        // 規則 2：授信使用率過高
        double utilization = (s.creditLimit == 0) ? 0 : s.loanBalance / s.creditLimit;
        if (utilization >= 0.85) {
            events.add(new CareEvent(s.clientId, "CREDIT_UTIL_HIGH", "授信使用率過高"));
        }

        // 規則 3：存款下滑（假設這裡拿不到昨天資料，只能硬塞）
        if (s.depositBalance < 200000) {
            events.add(new CareEvent(s.clientId, "DEPOSIT_LOW", "存款偏低，建議關懷"));
        }

        // 規則 4：逾期
        if (s.overdue) {
            events.add(new CareEvent(s.clientId, "OVERDUE", "客戶已有逾期"));
        }

        return events;
    }
}
```

### 真實場景需求

你要設計一個「關懷事件觸發引擎」，讓風險/業務單位未來可以**不改引擎核心**就加新規則。

**資料模型（可沿用或微調）**

* `ClientSnapshot`：某客戶某日快照資料
  至少包含：`clientId, loanBalance, creditLimit, depositBalance, bondPnLPercent, overdue`

**既有規則（先做這 4 條）**

1. `BOND_PNL_DROP`：bondPnLPercent <= -8.0
2. `CREDIT_UTIL_HIGH`：loanBalance / creditLimit >= 0.85（creditLimit=0 要安全處理）
3. `DEPOSIT_LOW`：depositBalance < 200000
4. `OVERDUE`：overdue = true

**擴充要求（核心）**

1. 未來新增規則（例如 `FX_RATE_SPIKE`、`LARGE_WITHDRAWAL`、`INDUSTRY_RISK_UP`）時，**不允許修改 `CareEventEngine` 的主流程**。
   你的設計要能做到：新增 class（或新增設定 + 對應 class）即可生效。
2. 每個事件要能追溯：

    * `reasonCode`（規則代碼）
    * `message`（人看得懂的說明）
    * **加分**：回傳 `ruleName` / `ruleVersion` 或 `triggeredFacts`（例如門檻值、計算出的 utilization）
3. 規則需要可開關（enable/disable）。

    * 最簡版本：在組裝引擎時決定哪些規則載入
    * 進階版本：規則物件自身帶 enabled 狀態