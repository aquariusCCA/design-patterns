package com.practice.lsp.demo2.good;

import java.util.Objects;

/**
 * 能力介面：只有「支援附件」的通道才需要實作它。
 */
public interface AttachmentCapableNotifier extends Notifier {

    void sendWithAttachment(User user, String message, Attachment attachment);

    static void validateAttachment(Attachment attachment) {
        Objects.requireNonNull(attachment, "attachment");
    }
}
