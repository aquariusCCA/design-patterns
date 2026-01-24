package com.practice.dip.demo2.good;

public class App {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        BatchJob job = new BatchJob(notifier);
        job.run();
    }
}