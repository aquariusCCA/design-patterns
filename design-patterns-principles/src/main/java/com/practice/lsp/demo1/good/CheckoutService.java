package com.practice.lsp.demo1.good;

import java.util.Objects;

public final class CheckoutService {
    private final DiscountPolicy discountPolicy;

    public CheckoutService(DiscountPolicy discountPolicy) {
        this.discountPolicy = Objects.requireNonNull(discountPolicy, "discountPolicy");
    }

    public Money checkout(Money original, Customer customer) {
        return discountPolicy.apply(original, customer);
    }
}

