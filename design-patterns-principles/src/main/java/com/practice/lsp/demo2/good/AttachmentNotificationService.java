package com.practice.lsp.demo2.good;

import java.util.Objects;

/**
 * 只負責附件通知：依賴 AttachmentCapableNotifier（保證有能力）。
 * 這樣就不會出現「傳入 SMS 後才在 runtime 爆掉」的 LSP 問題。
 */
public final class AttachmentNotificationService {
    private final AttachmentCapableNotifier notifier;

    public AttachmentNotificationService(AttachmentCapableNotifier notifier) {
        this.notifier = Objects.requireNonNull(notifier, "notifier");
    }

    public void notifyWithAttachment(User user, String message, Attachment attachment) {
        notifier.sendWithAttachment(user, message, attachment);
    }
}
