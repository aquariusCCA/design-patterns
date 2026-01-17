package com.practice.lsp.demo1.good;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * LSP 友善的 VIP 折扣：
 * - VIP：套用折扣
 * - 非 VIP：不折扣，回傳 original（等同 0% 折扣）
 *
 * 這樣任何使用 DiscountPolicy 的客戶端，都不需要知道子類細節，也不會被例外中斷。
 */
public final class VipDiscountPolicy implements DiscountPolicy {
    private final BigDecimal discountRate; // 例如 0.10 表示 10% off

    public VipDiscountPolicy(BigDecimal discountRate) {
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

        if (!customer.isVip()) {
            return original; // 重要：不丟例外、不強化前置條件
        }

        Money discount = original.multiply(discountRate);
        Money payable = original.subtract(discount);

        // 防禦式夾限：確保回傳值落在 [0, original]
        return payable.max(Money.zero()).min(original);
    }
}