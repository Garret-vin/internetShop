package com.service.impl;

import com.dao.BasketDao;
import com.factory.BasketDaoFactory;
import com.model.Basket;
import com.service.BasketService;

import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public void add(Basket basket) {
        basketDao.add(basket);
    }

    @Override
    public void addProduct(Long userId, Long productId) {
        basketDao.addProduct(userId, productId);
    }

    @Override
    public int size(Long userId) {
        return basketDao.size(userId);
    }

    @Override
    public Optional<Basket> getBasketByUserId(Long userId) {
        return basketDao.getBasketByUserId(userId);
    }
}
