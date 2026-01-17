package com.practice.lsp.demo2.good;

public final class Demo {

    public static void main(String[] args) {
        User user = new User("U-001", "u001@example.com", "0912-345-678");
        Attachment attachment = new Attachment("report.pdf", "https://example.com/report.pdf");

        System.out.println("=== Plain notify: all channels are substitutable ===");
        runPlain(new EmailNotifier(), user);
        runPlain(new SmsNotifier(), user);
        runPlain(new InAppNotifier(), user);

        System.out.println("\n=== Attachment notify: only attachment-capable channel is allowed ===");
        runAttachment(new EmailNotifier(), user, attachment);

        // 下面兩行如果你取消註解，會「編譯期」就擋住，而不是 runtime 才爆炸：
        // runAttachment(new SmsNotifier(), user, attachment);
        // runAttachment(new InAppNotifier(), user, attachment);

        System.out.println("\nAll demo checks passed (by design).");
    }

    private static void runPlain(Notifier notifier, User user) {
        NotificationService service = new NotificationService(notifier);
        service.notifyPlain(user, "Hello plain message");
    }

    private static void runAttachment(AttachmentCapableNotifier notifier, User user, Attachment attachment) {
        AttachmentNotificationService service = new AttachmentNotificationService(notifier);
        service.notifyWithAttachment(user, "Hello with attachment", attachment);
    }
}
