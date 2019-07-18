package com.model;

public class Order {

    private User user;
    private Basket basket;
    private Code code;
    private String email;
    private String phoneNumber;
    private String address;

    public Order(User user, Basket basket, Code code,
                 String email, String phoneNumber, String address) {
        this.user = user;
        this.basket = basket;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getConfirmCode() {
        return code.getCode();
    }

    public User getUser() {
        return user;
    }

    public Basket getBasket() {
        return basket;
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
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", basket=" + basket +
                ", code=" + code +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
