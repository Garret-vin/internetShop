package com.dao.impl;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.Product;
import com.utils.Database;
import org.apache.log4j.Logger;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    @Override
    public void addBasket(Basket basket) {
        Database.baskets.add(basket);
        logger.info(basket + " added to DB");
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        basket.getProductList().add(product);
        logger.info(product + " was added to " + basket);
    }
}
