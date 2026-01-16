package com.practice.ocp.demo1.good;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService(DiscountRegistry.loadDiscounts());
        System.out.println(discountService.discount("NORMAL", 100));
        System.out.println(discountService.discount("VIP", 100));
        System.out.println(discountService.discount("EMP", 100));
    }
}
