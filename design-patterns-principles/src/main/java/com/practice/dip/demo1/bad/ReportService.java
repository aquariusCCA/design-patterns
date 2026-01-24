package com.practice.dip.demo1.bad;

import java.time.LocalDate;
import java.util.List;

public class ReportService {
    // 違反 DIP：高層模組直接依賴低層具體類
    private final ExcelExporter exporter = new ExcelExporter();

    public void exportDailyReport(LocalDate date) {
        List<String> rows = queryRows(date);
        byte[] fileBytes = exporter.export(rows);
        System.out.println("exported excel bytes=" + fileBytes.length);
    }

    private List<String> queryRows(LocalDate date) {
        // 模擬資料查詢
        return List.of(
                "date=" + date,
                "depositForecast=123",
                "loanForecast=456"
        );
    }
}