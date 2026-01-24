package com.practice.dip.demo2.good;

public class EmailNotifier implements Notifier{
    @Override
    public void send(String subject, String message) {
        System.out.println("[EMAIL] subject=" + subject + ", message=" + message);
    }
}
