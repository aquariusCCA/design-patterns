package com.practice.lsp.demo1.bad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 簡單的不可變金額值物件（Value Object）。
 * - 小數位數：2 位
 * - 四捨五入：HALF_UP
 */
public final class Money implements Comparable<Money> {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = normalize(amount);
    }

    public static Money of(long value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money of(BigDecimal value) {
        return new Money(value);
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    public BigDecimal asBigDecimal() {
        return amount;
    }

    public Money multiply(BigDecimal factor) {
        Objects.requireNonNull(factor, "factor");
        return new Money(this.amount.multiply(factor));
    }

    public Money subtract(Money other) {
        Objects.requireNonNull(other, "other");
        return new Money(this.amount.subtract(other.amount));
    }

    public Money min(Money other) {
        Objects.requireNonNull(other, "other");
        return this.compareTo(other) <= 0 ? this : other;
    }

    public Money max(Money other) {
        Objects.requireNonNull(other, "other");
        return this.compareTo(other) >= 0 ? this : other;
    }

    public boolean isNegative() {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    @Override
    public int compareTo(Money o) {
        return this.amount.compareTo(o.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return this.amount.equals(other.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }

    private static BigDecimal normalize(BigDecimal value) {
        Objects.requireNonNull(value, "amount");
        return value.setScale(SCALE, ROUNDING);
    }
}