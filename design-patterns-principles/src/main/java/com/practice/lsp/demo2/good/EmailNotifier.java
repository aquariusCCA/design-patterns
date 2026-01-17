package com.practice.lsp.demo2.good;

public final class EmailNotifier implements AttachmentCapableNotifier {

    @Override
    public void send(User user, String message) {
        Notifier.validate(user, message);
        System.out.println("[EMAIL] to=" + user.getEmail() + " msg=" + message);
    }

    @Override
    public void sendWithAttachment(User user, String message, Attachment attachment) {
        Notifier.validate(user, message);
        AttachmentCapableNotifier.validateAttachment(attachment);

        System.out.println("[EMAIL] to=" + user.getEmail()
                + " msg=" + message
                + " attachment=" + attachment.getFileName()
                + " (" + attachment.getUrl() + ")");
    }
}
