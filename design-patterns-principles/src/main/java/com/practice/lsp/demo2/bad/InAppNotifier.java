package com.practice.lsp.demo2.bad;


/**
 * 可選：另一個會踩雷的例子。
 * 若站內訊息不支援附件，常見做法也是丟 UnsupportedOperationException。
 */
public final class InAppNotifier implements Notifier {

    @Override
    public void send(User user, String message) {
        Notifier.validate(user, message);
        System.out.println("[IN-APP] userId=" + user.getUserId() + " msg=" + message);
    }

    @Override
    public void sendWithAttachment(User user, String message, Attachment attachment) {
        Notifier.validate(user, message);
        throw new UnsupportedOperationException("In-app message does not support attachments");
    }
}
