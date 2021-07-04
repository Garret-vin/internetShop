package com.service.impl;

import com.dao.BasketDao;
import com.factory.BasketDaoFactory;
import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;

import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public void add(Basket basket) {
        basketDao.add(basket);
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        basketDao.addProduct(basket, product);
    }

    @Override
    public int size(Basket basket) {
        return basketDao.size(basket);
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        return basketDao.getBasketByUser(user);
    }
}
