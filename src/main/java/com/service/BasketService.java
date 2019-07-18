package com.service;

import com.model.Basket;
import com.model.Product;

public interface BasketService {

    void add(Basket basket);

    void addProduct(Basket basket, Product product);
}
