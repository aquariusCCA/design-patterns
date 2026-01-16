package com.practice.ocp.demo2.good.rule;

import com.practice.ocp.demo2.good.CareEvent;
import com.practice.ocp.demo2.good.ClientSnapshot;

import java.util.Optional;

public class CreditUtilHighRule implements CareRule {
    private final double creditLimit;
    private final boolean enabled;

    public CreditUtilHighRule(double creditLimit, boolean enabled) {
        this.creditLimit = creditLimit;
        this.enabled = enabled;
    }

    @Override
    public String code() {
        return "CREDIT_UTIL_HIGH";
    }

    @Override
    public boolean enabled() {
        return enabled;
    }

    @Override
    public Optional<CareEvent> evaluate(ClientSnapshot s) {
        double utilization = (s.creditLimit() == 0) ? 0 : s.loanBalance() / s.creditLimit();
        if (utilization >= creditLimit) {
            return Optional.of(new CareEvent(
                    s.clientId(),
                    code(),
                    "授信使用率過高"
            ));
        }
        return Optional.empty();
    }
}
