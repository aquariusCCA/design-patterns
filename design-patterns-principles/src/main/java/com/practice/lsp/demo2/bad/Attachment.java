package com.practice.lsp.demo2.bad;

import java.util.Objects;

public final class Attachment {
    private final String fileName;
    private final String url; // 簡化：用 URL/路徑代表附件位置

    public Attachment(String fileName, String url) {
        this.fileName = Objects.requireNonNull(fileName, "fileName");
        this.url = Objects.requireNonNull(url, "url");
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }
}
