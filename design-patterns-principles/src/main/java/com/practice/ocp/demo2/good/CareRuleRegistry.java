package com.practice.ocp.demo2.good;

import com.practice.ocp.demo2.good.rule.BondPnlDropRule;
import com.practice.ocp.demo2.good.rule.CareRule;
import com.practice.ocp.demo2.good.rule.CreditUtilHighRule;
import com.practice.ocp.demo2.good.rule.DepositLowRule;

import java.util.*;

public class CareRuleRegistry {
    private CareRuleRegistry(){}

    public static List<CareRule> defaultRules() {
        return List.of(
                new BondPnlDropRule(-8.0, true),
                new CreditUtilHighRule(0.85, true),
                new DepositLowRule(200000, true)
        );
    }
}
