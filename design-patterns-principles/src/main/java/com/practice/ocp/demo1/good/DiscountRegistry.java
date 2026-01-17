package com.practice.ocp.demo1.good;

import com.practice.ocp.demo1.good.discount.Discount;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;

public final class DiscountRegistry {
    private DiscountRegistry() {}

    public static Map<String, Discount> loadDiscounts() {
        // NOTE: java-spi编程实践
        ServiceLoader<Discount> loader = ServiceLoader.load(Discount.class);
        Map<String, Discount> map = new HashMap<>();

        for (Discount d : loader) {
            String key = d.type();
            if (key == null || key.isBlank()) {
                throw new IllegalStateException("Blank discount type: " + d.getClass().getName());
            }

            // 当你希望你的代码在任何地方都表现出相同的文本处理行为时，就使用 java.util.Locale.ROOT。
            key = key.trim().toUpperCase(Locale.ROOT);

            Discount old = map.put(key, d);
            if (old != null) {
                throw new IllegalStateException("Duplicate discount type: " + key
                        + " (" + old.getClass().getName() + " vs " + d.getClass().getName() + ")");
            }
        }

        if (map.isEmpty()) {
            throw new IllegalStateException("No Discount providers found. Check META-INF/services/<Discount FQN>.");
        }

        // 不可变Map
        return Map.copyOf(map);
    }
}