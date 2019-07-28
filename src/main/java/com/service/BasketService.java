package com.service;

import com.model.User;

public interface BasketService {

    void addProduct(Long userId, Long productId);

    int size(Long userId);

    void clean(User user);
}
