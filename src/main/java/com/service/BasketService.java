package com.service;

import com.model.Basket;

import java.util.Optional;

public interface BasketService {

    void add(Basket basket);

    void addProduct(Long userId, Long productId);

    int size(Long userId);

    Optional<Basket> getBasketByUserId(Long userId);
}
