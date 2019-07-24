package com.model;

import java.util.List;
import java.util.Objects;

public class Basket {

    private Long id;
    private Long userId;
    private List<Product> productList;

    public Basket() {
    }

    public Basket(Long userId, List<Product> productList) {
        this.userId = userId;
        this.productList = productList;
    }

    public Basket(Long id, Long userId, List<Product> productList) {
        this.id = id;
        this.userId = userId;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(id, basket.id) &&
                Objects.equals(userId, basket.userId) &&
                Objects.equals(productList, basket.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productList);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
