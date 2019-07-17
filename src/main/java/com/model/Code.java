package com.model;

import java.util.Random;

public class Code {

    private User user;
    private String code;

    public Code(User user) {
        this.user = user;
        this.code = generate();
    }

    public User getUser() {
        return user;
    }

    public String getCode() {
        return code;
    }

    private String generate() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }
}
