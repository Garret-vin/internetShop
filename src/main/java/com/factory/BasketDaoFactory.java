package com.factory;

import com.dao.BasketDao;
import com.dao.impl.hibernate.BasketHibDaoImpl;

public class BasketDaoFactory {

    private static BasketDao instance;

    private BasketDaoFactory() {
    }

    public static BasketDao getInstance() {
        if (instance == null) {
            instance = new BasketHibDaoImpl();
        }
        return instance;
    }
}
