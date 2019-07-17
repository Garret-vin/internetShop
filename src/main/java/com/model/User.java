package com.model;

import com.utils.IdGeneratorUtil;

import java.util.List;

public class User {

    private Long id;
    private Basket basket;
    private String email;
    private String login;
    private String password;
    private String role;

    public User(String email, String login, String password, String role) {
        this.id = IdGeneratorUtil.getUserId();
        this.basket = new Basket();
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBasketSize() {
        return basket.getSize();
    }

    public List<Product> getBasketList() {
        return basket.getProductList();
    }

    public void addProductToBasket(Product product) {
        basket.add(product);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
