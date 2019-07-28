package com.service.impl;

import com.dao.BasketDao;
import com.factory.BasketDaoFactory;
import com.model.User;
import com.service.BasketService;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public void addProduct(Long userId, Long productId) {
        basketDao.addProduct(userId, productId);
    }

    @Override
    public int size(Long userId) {
        return basketDao.size(userId);
    }

    @Override
    public void clean(User user) {
        basketDao.clean(user);
    }
}
