package com.practice.ocp.demo2.bad;

import java.util.ArrayList;
import java.util.List;

public class CareEventEngine_Bad {

    public List<CareEvent> detect(ClientSnapshot s) {
        List<CareEvent> events = new ArrayList<>();

        // 規則 1：債券損益過大
        if (s.bondPnLPercent <= -8.0) {
            events.add(new CareEvent(s.clientId, "BOND_PNL_DROP", "債券損益下跌超過門檻"));
        }

        // 規則 2：授信使用率過高
        double utilization = (s.creditLimit == 0) ? 0 : s.loanBalance / s.creditLimit;
        if (utilization >= 0.85) {
            events.add(new CareEvent(s.clientId, "CREDIT_UTIL_HIGH", "授信使用率過高"));
        }

        // 規則 3：存款下滑（假設這裡拿不到昨天資料，只能硬塞）
        if (s.depositBalance < 200000) {
            events.add(new CareEvent(s.clientId, "DEPOSIT_LOW", "存款偏低，建議關懷"));
        }

        // 規則 4：逾期
        if (s.overdue) {
            events.add(new CareEvent(s.clientId, "OVERDUE", "客戶已有逾期"));
        }

        return events;
    }
}