package com.practice.dip.demo1.good;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelExporter implements Exporter {
    @Override
    public byte[] export(List<String> rows) {
        String content = "EXCEL\n" + String.join("\n", rows);
        return content.getBytes(StandardCharsets.UTF_8);
    }
}
