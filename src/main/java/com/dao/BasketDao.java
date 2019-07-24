package com.dao;

import com.model.Basket;

import java.util.Optional;

public interface BasketDao {

    void add(Basket basket);

    void addProduct(Long userId, Long productId);

    int size(Long userId);

    Optional<Basket> getBasketByUserId(Long userId);
}
