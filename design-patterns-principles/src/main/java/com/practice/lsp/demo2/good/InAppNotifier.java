package com.practice.lsp.demo2.good;

public final class InAppNotifier implements Notifier {

    @Override
    public void send(User user, String message) {
        Notifier.validate(user, message);
        System.out.println("[IN-APP] userId=" + user.getUserId() + " msg=" + message);
    }
}
