package com.practice.ocp.demo1.good;

import com.practice.ocp.demo1.good.discount.Discount;

import java.util.Locale;
import java.util.Map;

public class DiscountService {
    private final Map<String, Discount> discounts;

    public DiscountService(Map<String, Discount> discounts) {
        //不可变Map
        this.discounts = Map.copyOf(discounts);
    }

    public double discount(String memberType, double amount) {
        // 当你希望你的代码在任何地方都表现出相同的文本处理行为时，就使用 java.util.Locale.ROOT。
        String key = memberType.trim().toUpperCase(Locale.ROOT);
        Discount d = discounts.get(key);
        if (d == null) throw new IllegalArgumentException("unknown type: " + memberType);
        return d.apply(amount);
    }
}
