package com.practice.lsp.demo2.bad;

import java.util.Objects;

public final class User {
    private final String userId;
    private final String email;
    private final String phone;

    public User(String userId, String email, String phone) {
        this.userId = Objects.requireNonNull(userId, "userId");
        this.email = Objects.requireNonNull(email, "email");
        this.phone = Objects.requireNonNull(phone, "phone");
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}

