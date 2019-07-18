package com.factory;

import com.service.BasketService;
import com.service.impl.BasketServiceImpl;

public class BasketServiceFactory {

    private static BasketService instance;

    private BasketServiceFactory() {
    }

    public static BasketService getInstance() {
        if (instance == null) {
            instance = new BasketServiceImpl();
        }
        return instance;
    }
}
