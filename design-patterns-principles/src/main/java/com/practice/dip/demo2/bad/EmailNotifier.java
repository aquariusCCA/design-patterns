package com.practice.dip.demo2.bad;

public class EmailNotifier {

    public void send(String subject, String message) {
        // 低層細節：這裡先用 console 模擬
        System.out.println("[EMAIL] subject=" + subject + ", message=" + message);
    }
}