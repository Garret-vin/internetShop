package com.dao;

import com.model.User;

public interface BasketDao {

    void addProduct(Long userId, Long productId);

    int size(Long userId);

    void clean(User user);
}
