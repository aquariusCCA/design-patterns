package com.practice.lsp.demo2.bad;

public final class EmailNotifier implements Notifier {

    @Override
    public void send(User user, String message) {
        Notifier.validate(user, message);
        System.out.println("[EMAIL] to=" + user.getEmail() + " msg=" + message);
    }

    @Override
    public void sendWithAttachment(User user, String message, Attachment attachment) {
        Notifier.validate(user, message);
        if (attachment == null) {
            throw new IllegalArgumentException("attachment must not be null");
        }
        System.out.println("[EMAIL] to=" + user.getEmail()
                + " msg=" + message
                + " attachment=" + attachment.getFileName()
                + " (" + attachment.getUrl() + ")");
    }
}