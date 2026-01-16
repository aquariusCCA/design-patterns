package com.practice.ocp.demo2.good.rule;

import com.practice.ocp.demo2.good.CareEvent;
import com.practice.ocp.demo2.good.ClientSnapshot;

import java.util.Optional;

public interface CareRule {
    String code();                 // reasonCode
    boolean enabled();             // 可開關
    Optional<CareEvent> evaluate(ClientSnapshot s);
}