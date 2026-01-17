package com.practice.lsp.demo2.bad;

/**
 * 壞示範（違反 LSP）：
 * - 父介面 Notifier 宣告了 sendWithAttachment
 * - 但 SMS 通道不支援附件
 * - 子類用 UnsupportedOperationException 逃避
 *
 * 結果：任何只依賴 Notifier 的客戶端，在替換成 SmsNotifier 後就可能壞掉。
 */
public final class SmsNotifier implements Notifier {

    @Override
    public void send(User user, String message) {
        Notifier.validate(user, message);
        System.out.println("[SMS] to=" + user.getPhone() + " msg=" + message);
    }

    @Override
    public void sendWithAttachment(User user, String message, Attachment attachment) {
        Notifier.validate(user, message);
        // LSP 違反點：父型別承諾有此能力，但子類做不到就直接炸掉
        throw new UnsupportedOperationException("SMS does not support attachments");
    }
}
