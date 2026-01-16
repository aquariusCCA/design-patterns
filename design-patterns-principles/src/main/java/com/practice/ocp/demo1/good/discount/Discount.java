package com.practice.ocp.demo1.good.discount;

public interface Discount {
    String type();          // e.g. "VIP"
    double apply(double amount);
}
