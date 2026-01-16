package com.practice.ocp.demo1.good.discount;

public class VipDiscount implements Discount {
    @Override
    public String type() { return "VIP"; }

    @Override
    public double apply(double amount) { return amount * 0.90; }
}

