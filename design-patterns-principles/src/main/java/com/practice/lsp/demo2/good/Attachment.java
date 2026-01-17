package com.practice.lsp.demo2.good;

import java.util.Objects;

public final class Attachment {
    private final String fileName;
    private final String url;

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
