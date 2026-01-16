package com.practice.ocp.demo1.bad;


/**
 * 需求:
 *      - 週要新增：GOLD_VIP、STUDENT、BLACKLIST（直接拒絕交易）
 *      - 折扣規則未來會頻繁變動，但「結帳流程」要穩定
 *
 * 你的任務
 *      - 重構成符合 OCP：新增會員類型時，不改 DiscountService 的核心流程（或至少不改其折扣分派邏輯）
 */
public class DiscountService {
    public double discount(String memberType, double amount) {
        if ("NORMAL".equals(memberType)) return amount * 0.95;
        if ("VIP".equals(memberType))    return amount * 0.90;
        if ("EMP".equals(memberType))    return amount * 0.80;
        throw new IllegalArgumentException("unknown type");
    }
}