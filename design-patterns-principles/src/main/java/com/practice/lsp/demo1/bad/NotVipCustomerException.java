package com.practice.lsp.demo1.bad;

public class NotVipCustomerException extends RuntimeException {
    public NotVipCustomerException(String message) {
        super(message);
    }
}
