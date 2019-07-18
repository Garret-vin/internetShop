package com.service.impl;

import com.dao.BasketDao;
import com.factory.BasketDaoFactory;
import com.model.Basket;
import com.model.Product;
import com.service.BasketService;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public void add(Basket basket) {
        basketDao.addBasket(basket);
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        basketDao.addProduct(basket, product);
    }
}
