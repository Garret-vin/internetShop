package com.service.impl;

import com.dao.OrderDao;
import com.factory.OrderDaoFactory;
import com.model.Order;
import com.service.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderDao.getById(id);
    }
}
