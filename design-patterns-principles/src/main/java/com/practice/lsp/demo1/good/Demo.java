package com.practice.lsp.demo1.good;

import java.math.BigDecimal;

public final class Demo {

    public static void main(String[] args) {
        Customer vip = new Customer("VIP-001", true);
        Customer normal = new Customer("N-001", false);
        Money original = Money.of("100.00");

        // 1) 用 VipDiscountPolicy 測試
        DiscountPolicy vip10Off = new VipDiscountPolicy(new BigDecimal("0.10")); // 10% off
        runScenario("VipDiscountPolicy(10% off)", vip10Off, original, vip, normal);

        // 2) 替換成 NoDiscountPolicy 測試（驗證可替換）
        DiscountPolicy noDiscount = new NoDiscountPolicy();
        runScenario("NoDiscountPolicy", noDiscount, original, vip, normal);

        System.out.println("\nAll checks passed.");
    }

    private static void runScenario(String name, DiscountPolicy policy, Money original, Customer vip, Customer normal) {
        System.out.println("\n=== " + name + " ===");

        CheckoutService service = new CheckoutService(policy);

        Money vipPayable = service.checkout(original, vip);
        Money normalPayable = service.checkout(original, normal);

        System.out.println("VIP payable    : " + vipPayable);
        System.out.println("Normal payable : " + normalPayable);

        // LSP / Contract checks（不使用 JUnit，自己丟 RuntimeException）
        ensureNotNull(vipPayable, "vipPayable must not be null");
        ensureNotNull(normalPayable, "normalPayable must not be null");

        ensureInRange(vipPayable, Money.zero(), original, "vipPayable out of [0, original]");
        ensureInRange(normalPayable, Money.zero(), original, "normalPayable out of [0, original]");

        // 額外的預期值檢查（針對特定策略）
        if (policy instanceof VipDiscountPolicy) {
            ensureEquals(Money.of("90.00"), vipPayable, "VIP should be 90.00 for 10% off");
            ensureEquals(original, normalPayable, "Normal should remain original when not VIP");
        } else if (policy instanceof NoDiscountPolicy) {
            ensureEquals(original, vipPayable, "VIP should remain original for NoDiscountPolicy");
            ensureEquals(original, normalPayable, "Normal should remain original for NoDiscountPolicy");
        }
    }

    private static void ensureInRange(Money value, Money min, Money max, String message) {
        if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
            throw new IllegalStateException(message + " -> value=" + value + ", min=" + min + ", max=" + max);
        }
    }

    private static void ensureEquals(Money expected, Money actual, String message) {
        if (!expected.equals(actual)) {
            throw new IllegalStateException(message + " -> expected=" + expected + ", actual=" + actual);
        }
    }

    private static void ensureNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalStateException(message);
        }
    }
}
