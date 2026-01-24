package com.practice.dip.demo1.good;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        ReportService service = new ReportService(new ExcelExporter());
        service.exportDailyReport(LocalDate.now());
    }
}
