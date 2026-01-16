package com.practice.ocp.demo2.good.rule;

import com.practice.ocp.demo2.good.CareEvent;
import com.practice.ocp.demo2.good.ClientSnapshot;

import java.util.Optional;

public class DepositLowRule implements CareRule {
    private final double depositBalance;
    private final boolean enabled;

    public DepositLowRule(double depositBalance, boolean enabled) {
        this.depositBalance = depositBalance;
        this.enabled = enabled;
    }


    @Override
    public String code() {
        return "DEPOSIT_LOW";
    }

    @Override
    public boolean enabled() {
        return enabled;
    }

    @Override
    public Optional<CareEvent> evaluate(ClientSnapshot s) {
        if (s.depositBalance() < depositBalance) {
            return Optional.of(new CareEvent(
                    s.clientId(),
                    code(),
                    "存款偏低，建議關懷"
            ));
        }
        return Optional.empty();
    }
}
