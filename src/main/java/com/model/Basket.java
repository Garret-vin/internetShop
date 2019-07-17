package com.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Product> productList;

    public Basket() {
        this.productList = new ArrayList<>();
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

}
