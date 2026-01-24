package com.practice.dip.demo2.bad;

public class BatchJob {
    public void run() {
        EmailNotifier notifier = new EmailNotifier(); // 違反 DIP：高層直接依賴具體類

        try {
            System.out.println("running batch job...");
            // 模擬處理
            doWork();
            notifier.send("Batch SUCCESS", "Job finished successfully.");
        } catch (Exception e) {
            notifier.send("Batch FAILED", "Job failed: " + e.getMessage());
        }
    }

    private void doWork() {
        // 你可以改成隨機失敗來測試
        if (System.currentTimeMillis() % 2 == 0) {
            throw new RuntimeException("DB timeout");
        }
    }
}