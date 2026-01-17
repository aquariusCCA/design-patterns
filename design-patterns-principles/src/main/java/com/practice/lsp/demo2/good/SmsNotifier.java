package com.practice.lsp.demo2.good;

public final class SmsNotifier implements Notifier {

    @Override
    public void send(User user, String message) {
        Notifier.validate(user, message);
        System.out.println("[SMS] to=" + user.getPhone() + " msg=" + message);
    }
}
