package com.practice.dip.demo1.good;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ReportService {

    private final Exporter exporter;

    public ReportService(Exporter exporter) {
        this.exporter = Objects.requireNonNull(exporter, "exporter must not be null");
    }

    public void exportDailyReport(LocalDate date) {
        List<String> rows = queryRows(date);
        byte[] fileBytes = exporter.export(rows);
        System.out.println("exported report bytes=" + fileBytes.length);
    }

    private List<String> queryRows(LocalDate date) {
        return List.of(
                "date=" + date,
                "depositForecast=123",
                "loanForecast=456"
        );
    }
}