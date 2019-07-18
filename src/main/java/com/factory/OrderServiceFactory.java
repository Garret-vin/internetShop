package com.factory;

import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

public class OrderServiceFactory {

    private static OrderService instance;

    private OrderServiceFactory() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }
}
