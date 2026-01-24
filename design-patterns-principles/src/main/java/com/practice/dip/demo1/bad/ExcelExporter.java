package com.practice.dip.demo1.bad;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelExporter {

    // 低層細節：用簡化方式模擬 excel bytes
    public byte[] export(List<String> rows) {
        String content = "EXCEL\n" + String.join("\n", rows);
        return content.getBytes(StandardCharsets.UTF_8);
    }
}