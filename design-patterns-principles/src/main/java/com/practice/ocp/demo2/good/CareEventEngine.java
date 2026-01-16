package com.practice.ocp.demo2.good;

import com.practice.ocp.demo2.good.rule.CareRule;

import java.util.ArrayList;
import java.util.List;

public class CareEventEngine {
    private final List<CareRule> rules;

    CareEventEngine(List<CareRule> rules) {
        this.rules = rules;
    }

    public List<CareEvent> detect(ClientSnapshot s) {
        List<CareEvent> out = new ArrayList<>();
        for (CareRule r : rules) {
            if (!r.enabled()) continue;
            r.evaluate(s).ifPresent(out::add);
        }
        return out;
    }
}
