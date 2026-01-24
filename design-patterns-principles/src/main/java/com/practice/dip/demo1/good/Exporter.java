package com.practice.dip.demo1.good;

import java.util.List;

public interface Exporter {
    byte[] export(List<String> rows);
}