package com.practice.lsp.demo1.good;

/**
 * 契約（LSP 的核心）：
 * 1) original 必須是非負數。
 * 2) 回傳金額必須落在 [0, original]。
 * 3) 必須能套用於任何 Customer（不可因「資格」問題而拋例外或要求客戶端特判）。
 */
public interface DiscountPolicy {
    Money apply(Money original, Customer customer);
}