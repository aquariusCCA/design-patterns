# 練習題 2：通知（Notification）與「不該強迫所有子類都能做到的行為」

### 情境

你有一個父型別 `Notifier`，一開始設計成：

* `void send(User user, String message)`
* `void sendWithAttachment(User user, String message, File attachment)`

後來你新增子類：

* `EmailNotifier`：可寄附件
* `SmsNotifier`：**不能寄附件**（SMS 無附件概念）
* `InAppNotifier`：只能站內訊息，附件也不一定支援

這很容易在 `SmsNotifier` 裡做出：

* `sendWithAttachment(...)` 直接 `throw UnsupportedOperationException`（高機率違反 LSP）

### 你要做的事

1. 先做出上述繼承設計（會違反 LSP）。
2. 建立 `NotificationService` 作為客戶端：它只依賴 `Notifier`，對外提供：

    * `notifyPlain(...)`
    * `notifyWithAttachment(...)`
3. 用測試寫出父型別契約（例如：「只要是 Notifier，就能處理 notifyWithAttachment」），然後用 `SmsNotifier` 替換，看測試爆掉。
4. 重構修正（符合 LSP），要求：

    * 讓不能寄附件的 notifier 也能替換父型別而不破壞契約
    * `NotificationService` 的 if/else 盡量少，不能靠 `instanceof`
    * 用介面分離、能力介面（Capability Interface）、組合模式或策略都可以

### 交付物（建議檔案）

* `com.practice.lsp.case2_notifier.bad.*`
* `com.practice.lsp.case2_notifier.good.*`
* `NotificationServiceTest`：至少 5 個測試

    * Plain message：所有 notifier 都能成功
    * With attachment：替換不同 notifier 行為仍符合你修正後的契約
    * 不可因子類不支援就炸掉（除非你把這寫成「契約允許」且一致）
    * message 空字串/長度限制（自己訂一個規則並落在父契約）

### 提示（不要一次用掉）

常見修正：拆成

* `Notifier`：只負責純文字
* `AttachmentCapableNotifier`：才有附件能力
  或把「附件」當成可選 payload（例如 `NotificationPayload`），讓所有 notifier 都能接受 payload，但不支援者會用「降級行為」(degrade gracefully) 並保持契約一致（例如改為上傳附件後送連結）。

---