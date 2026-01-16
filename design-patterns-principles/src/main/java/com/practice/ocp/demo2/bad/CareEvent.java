package com.practice.ocp.demo2.bad;

public class CareEvent {
    String clientId;
    String reasonCode;
    String message;

    CareEvent(String clientId, String reasonCode, String message) {
        this.clientId = clientId;
        this.reasonCode = reasonCode;
        this.message = message;
    }
}
