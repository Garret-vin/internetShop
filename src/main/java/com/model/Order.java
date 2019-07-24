package com.model;

import java.util.Objects;

public class Order {

    private Long id;
    private Long basketId;
    private User user;
    private Code code;
    private String email;
    private String phoneNumber;
    private String address;

    public Order() {
    }

    public Order(Long basketId, User user, Code code,
                 String email, String phoneNumber, String address) {
        this.basketId = basketId;
        this.user = user;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Order(Long id, Long basketId, User user, Code code,
                 String email, String phoneNumber, String address) {
        this.id = id;
        this.basketId = basketId;
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

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(basketId, order.basketId) &&
                Objects.equals(user, order.user) &&
                Objects.equals(code, order.code) &&
                Objects.equals(email, order.email) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basketId, user, code, email, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", basketId=" + basketId +
                ", user=" + user +
                ", code=" + code +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
