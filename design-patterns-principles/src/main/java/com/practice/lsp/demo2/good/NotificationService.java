package com.practice.lsp.demo2.good;

import java.util.Objects;

/**
 * 只負責純文字通知：依賴 Notifier（任何通道都能替換）。
 */
public final class NotificationService {
    private final Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = Objects.requireNonNull(notifier, "notifier");
    }

    public void notifyPlain(User user, String message) {
        notifier.send(user, message);
    }
}
