package com.practice.lsp.demo2.bad;

public final class Demo {
    public static void main(String[] args) {
        User user = new User("U-001", "u001@example.com", "0912-345-678");
        Attachment attachment = new Attachment("report.pdf", "https://example.com/report.pdf");

        // 1) Email：正常
        NotificationService emailService = new NotificationService(new EmailNotifier());
        emailService.notifyPlain(user, "Hello (email plain)");
        emailService.notifyWithAttachment(user, "Hello (email with attachment)", attachment);

        System.out.println();

        // 2) SMS：替換成 SmsNotifier 後，客戶端呼叫附件通知會直接炸掉（LSP 違反展示）
        NotificationService smsService = new NotificationService(new SmsNotifier());
        smsService.notifyPlain(user, "Hello (sms plain)");

        System.out.println("About to send SMS with attachment (this will fail)...");
        smsService.notifyWithAttachment(user, "Hello (sms with attachment)", attachment); // 這行會丟例外
    }
}
