package com.practice.lsp.demo1.bad;

import java.util.Objects;
public final class CheckoutService {
    private final DiscountPolicy discountPolicy;

    public CheckoutService(DiscountPolicy discountPolicy) {
        this.discountPolicy = Objects.requireNonNull(discountPolicy, "discountPolicy");
    }

    public Money checkout(Money original, Customer customer) {
        // 客戶端期待：任何 DiscountPolicy 都能對任何 customer 正常運作，而不是動不動就炸掉。
        return discountPolicy.apply(original, customer);
    }
}