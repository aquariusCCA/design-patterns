# 練習題 1：折扣策略（很容易踩到「子類強化前置條件」）

### 情境

你在電商系統有一個父型別 `DiscountPolicy`：

* `Money apply(Money original, Customer customer)`：回傳折扣後金額
* 父類契約（contract）：

    1. `original` 為非負數即可
    2. 回傳金額必須介於 `0` 到 `original`（不可負、不可比原價高）
    3. **不應因為顧客型別不同而丟例外**（除非父類契約就允許）

### 你要做的事

1. 先做一個子類 `VipOnlyDiscountPolicy`：只有 VIP 才能折扣，非 VIP 直接丟例外（這會很可能違反 LSP）。
2. 寫一個 `CheckoutService`（客戶端）只依賴 `DiscountPolicy`，不做 `instanceof` 判斷。
3. 寫測試把父類契約寫死，然後把 `VipOnlyDiscountPolicy` 塞進去跑，觀察哪些測試會爆。
4. 重構修正（符合 LSP），要求：

    * `CheckoutService` 不改（或只改最少）
    * 不用 `instanceof`
    * 子類可被替換後仍滿足契約

### 交付物（建議檔案）

* `com.practice.lsp.case1_discount.bad.*`（違反 LSP 的版本）
* `com.practice.lsp.case1_discount.good.*`（修正版）
* `CheckoutServiceTest`：至少 4 個測試

    * 非 VIP 不應炸掉（父契約）
    * 折扣後金額範圍必須合法
    * original=0 的邊界
    * 任意 policy 替換都能過（用參數化/動態替換都行）

### 提示（不要一次用掉）

修正方向通常是：把「是否可折扣」抽成策略/規則（Policy/Rule），或拆介面（例如 `ConditionalDiscountPolicy`），或回傳 `Optional<Money>`（如果你願意修改契約），但要一致。

---