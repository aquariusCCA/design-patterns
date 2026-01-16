package com.practice.ocp.demo1.good.discount;

public class NormalDiscount implements Discount {
    @Override
    public String type() {
        return "NORMAL";
    }

    @Override
    public double apply(double amount) {
        return amount * 0.95;
    }
}
