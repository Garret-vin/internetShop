package com.factory;

import com.dao.ProductDao;
import com.dao.impl.ProductMySQLDaoImpl;

public class ProductDaoFactory {

    private static ProductDao instance;

    private ProductDaoFactory() {
    }

    public static synchronized ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductMySQLDaoImpl();
        }
        return instance;
    }
}
