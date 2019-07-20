package com.model;

import java.util.Objects;

public class Order {

    private Long id;
    private User user;
    private Code code;
    private String email;
    private String phoneNumber;
    private String address;

    public Order(User user, Code code,
                 String email, String phoneNumber, String address) {
        this.user = user;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Order(Long id, User user, Code code, String email, String phoneNumber, String address) {
        this.id = id;
        this.user = user;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Code getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                user.equals(order.user) &&
                code.equals(order.code) &&
                email.equals(order.email) &&
                phoneNumber.equals(order.phoneNumber) &&
                address.equals(order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, code, email, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", code=" + code +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
