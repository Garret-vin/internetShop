package com.model;

import com.utils.CodeGeneratorUtil;

import java.util.Objects;

public class Code {

    private Long id;
    private String value;
    private User user;

    public Code(User user) {
        this.user = user;
        this.value = CodeGeneratorUtil.generate();
    }

    public Code(Long id, String value, User user) {
        this.id = id;
        this.user = user;
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(id, code.id) &&
                Objects.equals(value, code.value) &&
                Objects.equals(user, code.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, user);
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", user=" + user +
                '}';
    }
}
