package com.practice.dip.demo1.bad;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        ReportService service = new ReportService();
        service.exportDailyReport(LocalDate.now());
    }
}