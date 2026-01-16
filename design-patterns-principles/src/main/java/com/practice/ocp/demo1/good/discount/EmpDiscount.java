package com.practice.ocp.demo1.good.discount;

public class EmpDiscount implements Discount {
    @Override
    public String type() {
        return "EMP";
    }

    @Override
    public double apply(double amount) {
        return amount * 0.8;
    }
}
