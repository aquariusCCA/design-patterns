package com.practice.ocp.demo2.good;

public record CareEvent(
    String clientId,
    String reasonCode,
    String message
) { }
