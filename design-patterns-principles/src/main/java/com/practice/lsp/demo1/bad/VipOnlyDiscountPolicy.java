package com.practice.lsp.demo1.bad;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 壞示範（違反 LSP）：
 * - 強化了前置條件：customer 必須是 VIP
 * - 非 VIP 會直接丟例外
 *
 * 若客戶端程式依賴 DiscountPolicy 的契約「可套用於任何 Customer」，
 * 那麼替換成此實作就會導致程式壞掉。
 */
public final class VipOnlyDiscountPolicy implements DiscountPolicy {
    private final BigDecimal discountRate; // e.g. 0.10 means 10% off

    public VipOnlyDiscountPolicy(BigDecimal discountRate) {
        Objects.requireNonNull(discountRate, "discountRate");
        if (discountRate.compareTo(BigDecimal.ZERO) < 0 || discountRate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("discountRate must be between 0 and 1");
        }
        this.discountRate = discountRate;
    }

    @Override
    public Money apply(Money original, Customer customer) {
        Objects.requireNonNull(original, "original");
        Objects.requireNonNull(customer, "customer");

        if (original.isNegative()) {
            throw new IllegalArgumentException("original must be non-negative");
        }

        /**
         * 1) 強化前置條件（Precondition）
         *     父型別契約允許「任何 Customer」都可以呼叫 apply。
         *     子類卻額外要求「Customer 必須 VIP」才行。
         *     這叫做：子類把可接受的輸入集合變小（更嚴格），典型 LSP 違反。
         * 2) 改變可觀察行為：引入客戶端未預期的例外
         *     對同一個輸入 (original=100, customer=非VIP)：
         *         另一個合理的 DiscountPolicy（例如 NoDiscountPolicy/FixedRatePolicy）很可能回傳 100 或某個折扣值
         *         VipOnlyDiscountPolicy 卻直接丟 NotVipCustomerException
         *     對客戶端來說，「本來能正常結帳 → 換一個 DiscountPolicy 之後直接爆炸」，這就是不可替換。
         */
        // LSP 違反點：額外的資格限制 + 以例外中斷流程
        if (!customer.isVip()) {
            throw new NotVipCustomerException("Customer " + customer.getCustomerId() + " is not VIP");
        }

        Money discount = original.multiply(discountRate);
        Money payable = original.subtract(discount);

        return payable.max(Money.zero()).min(original);
    }
}