package com.practice.lsp.demo1.bad;

/**
 * 契約（客戶端的期待）：
 * 1) original 必須是非負數。
 * 2) 回傳的應付金額必須落在 [0, original] 的範圍內（不可為負、不可大於原價）。
 * 3) 應能套用於任何 Customer（不應額外增加「資格限制」）。
 *
 * 注意：下面的「bad」實作會違反第 (3) 點。
 */
public interface DiscountPolicy {
    Money apply(Money original, Customer customer);
}
