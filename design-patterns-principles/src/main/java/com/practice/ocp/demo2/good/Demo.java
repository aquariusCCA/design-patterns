package com.practice.ocp.demo2.good;

import com.practice.ocp.demo2.good.rule.CareRule;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<CareRule> rules = CareRuleRegistry.defaultRules();
        CareEventEngine engine = new CareEventEngine(rules);
        ClientSnapshot s = new ClientSnapshot(
                "C001",
                900000,    // loan
                1000000,   // limit
                150000,    // deposit
                -9.2,      // bond pnl %
                true       // overdue
        );

        List<CareEvent> events = engine.detect(s);
        for (CareEvent e : events) {
            System.out.println(e);
        }
    }
}
