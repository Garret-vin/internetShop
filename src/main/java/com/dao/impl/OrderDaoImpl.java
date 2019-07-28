package com.dao.impl;

import com.dao.OrderDao;
import com.model.Order;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void add(Order order) {
        order.setId(IdGeneratorUtil.getOrderId());
        Database.orders.add(order);
        logger.info(order + " added to DB");
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Database.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Order> getLastOrderForUser(User user) {
        return Database.orders
                .stream()
                .filter(order -> order.getUser().equals(user))
                .min((o1, o2) -> (int) (o2.getId() - o1.getId()));
    }
}
