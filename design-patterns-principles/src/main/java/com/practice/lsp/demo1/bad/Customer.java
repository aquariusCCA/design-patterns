package com.practice.lsp.demo1.bad;

import java.util.Objects;

public final class Customer {
    private final String customerId;
    private final boolean vip;

    public Customer(String customerId, boolean vip) {
        this.customerId = Objects.requireNonNull(customerId, "customerId");
        this.vip = vip;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean isVip() {
        return vip;
    }
}