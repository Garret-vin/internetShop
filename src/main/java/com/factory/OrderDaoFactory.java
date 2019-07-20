package com.factory;

import com.dao.OrderDao;
import com.dao.impl.OrderMySQLDaoImpl;

public class OrderDaoFactory {

    private static OrderDao instance;

    private OrderDaoFactory() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderMySQLDaoImpl();
        }
        return instance;
    }
}
