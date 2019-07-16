package com.model;

import java.util.UUID;

public class Code {

    private User user;
    private String code;

    public Code(User user) {
        this.user = user;
        this.code = UUID.randomUUID().toString().substring(0, 4);
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
