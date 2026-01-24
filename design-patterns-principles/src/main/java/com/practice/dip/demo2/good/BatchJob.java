package com.practice.dip.demo2.good;

import java.util.Objects;

public class BatchJob {
    private final Notifier notifier;


    public BatchJob(Notifier notifier) {
        this.notifier = Objects.requireNonNull(notifier, "notifier must not be null");
    }

    public void run() {
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