package com.dao.impl;

import com.dao.OrderDao;
import com.model.Order;
import com.utils.Database;
import org.apache.log4j.Logger;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void add(Order order) {
        Database.orders.add(order);
        logger.info(order + " added to DB");
    }
}
