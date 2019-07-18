package com.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private User user;
    private List<Product> productList;

    public Basket(User user) {
        this.user = user;
        this.productList = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public int getSize() {
        return productList.size();
    }

    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "user=" + user +
                ", productList=" + productList +
                '}';
    }
}
