package com.practice.lsp.demo2.bad;

import java.util.Objects;

/**
 * 客戶端只依賴 Notifier（父型別）：
 * - 如果 LSP 成立：替換任何 Notifier 都不該讓這裡壞掉
 * - 但因為父介面契約設計不合理，某些子類做不到，替換就會炸
 */
public final class NotificationService {

    private final Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = Objects.requireNonNull(notifier, "notifier");
    }

    public void notifyPlain(User user, String message) {
        notifier.send(user, message);
    }

    public void notifyWithAttachment(User user, String message, Attachment attachment) {
        notifier.sendWithAttachment(user, message, attachment);
    }
}
