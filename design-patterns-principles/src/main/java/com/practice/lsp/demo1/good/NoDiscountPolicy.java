package com.practice.lsp.demo1.good;

import java.util.Objects;

public final class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public Money apply(Money original, Customer customer) {
        Objects.requireNonNull(original, "original");
        Objects.requireNonNull(customer, "customer");

        if (original.isNegative()) {
            throw new IllegalArgumentException("original must be non-negative");
        }
        return original;
    }
}
