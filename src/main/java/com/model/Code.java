package com.model;

import java.util.Random;

public class Code {

    private User user;
    private String code;

    public Code(User user) {
        this.user = user;
        this.code = String.valueOf(new Random().nextInt(9001)+1000);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
