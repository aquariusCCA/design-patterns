package com.practice.lsp.demo2.bad;

import java.util.Objects;

/**
 * 壞設計（為了示範 LSP 違反）：
 * 這個父介面把「純文字通知」與「附件通知」綁在一起。
 * 等同於宣告：任何 Notifier 都必須支援 sendWithAttachment。
 *
 * 但像 SMS 這種通道，天然不支援附件，子類就只能丟例外，進而違反 LSP。
 */
public interface Notifier {

    void send(User user, String message);

    void sendWithAttachment(User user, String message, Attachment attachment);

    static void validate(User user, String message) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(message, "message");
        if (message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
    }
}
