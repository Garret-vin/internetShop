package com.factory;

import com.service.ProductService;
import com.service.impl.ProductServiceImpl;

public class ProductServiceFactory {

    private static ProductService instance;

    private ProductServiceFactory() {
    }

    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }
}
