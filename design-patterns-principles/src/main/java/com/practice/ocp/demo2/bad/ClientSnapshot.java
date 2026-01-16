package com.practice.ocp.demo2.bad;

public class ClientSnapshot {
    String clientId;
    double loanBalance;        // 放款餘額
    double creditLimit;        // 授信額度
    double depositBalance;     // 存款餘額
    double bondPnLPercent;     // 債券浮動損益(%)，例如 -8.5 表示 -8.5%
    boolean overdue;           // 是否逾期
}