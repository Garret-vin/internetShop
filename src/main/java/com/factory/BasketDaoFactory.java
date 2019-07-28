package com.factory;

import com.dao.BasketDao;
import com.dao.impl.BasketMySQLDaoImpl;

public class BasketDaoFactory {

    private static BasketDao instance;

    private BasketDaoFactory() {
    }

    public static BasketDao getInstance() {
        if (instance == null) {
            instance = new BasketMySQLDaoImpl();
        }
        return instance;
    }
}
