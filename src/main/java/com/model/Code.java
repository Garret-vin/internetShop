package com.model;

import com.utils.CodeGeneratorUtil;

public class Code {

    private User user;
    private String code;

    public Code(User user) {
        this.user = user;
        this.code = CodeGeneratorUtil.generate();
    }

    public User getUser() {
        return user;
    }

    public String getCode() {
        return code;
    }
}
