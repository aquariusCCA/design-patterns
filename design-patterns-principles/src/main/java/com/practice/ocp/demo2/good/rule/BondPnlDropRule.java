package com.practice.ocp.demo2.good.rule;

import com.practice.ocp.demo2.good.CareEvent;
import com.practice.ocp.demo2.good.ClientSnapshot;

import java.util.Optional;

public class BondPnlDropRule implements CareRule {
    private final double threshold;
    private final boolean enabled;

    public BondPnlDropRule(double threshold, boolean enabled) {
        this.threshold = threshold;
        this.enabled = enabled;
    }

    @Override public String code() { return "BOND_PNL_DROP"; }
    @Override public boolean enabled() { return enabled; }

    @Override
    public Optional<CareEvent> evaluate(ClientSnapshot s) {
        if (s.bondPnLPercent() <= threshold) {
            return Optional.of(new CareEvent(
                    s.clientId(),
                    code(),
                    "債券損益下跌超過門檻：" + threshold + "% (實際 " + s.bondPnLPercent() + "%)"
            ));
        }
        return Optional.empty();
    }
}