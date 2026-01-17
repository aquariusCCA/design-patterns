package com.practice.lsp.demo2.good;

import java.util.Objects;

/**
 * 只定義「所有通道都能做到」的能力：送出純文字通知。
 */
public interface Notifier {

    void send(User user, String message);

    static void validate(User user, String message) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(message, "message");
        if (message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
    }
}
