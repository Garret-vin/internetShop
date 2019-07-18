package com.dao;

import com.model.Basket;
import com.model.Product;

public interface BasketDao {

    void addBasket(Basket basket);

    void addProduct(Basket basket, Product product);
}
