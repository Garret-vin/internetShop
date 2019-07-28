package com.dao.impl;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    @Override
    public void addProduct(Long userId, Long productId) {
        Basket basket = new Basket(userId, productId);
        basket.setId(IdGeneratorUtil.getBasketId());
        Database.baskets.add(basket);
        logger.info(basket + " was added to DB");
    }

    @Override
    public int size(Long userId) {
        return (int) Database.baskets.stream()
                .filter(basket -> basket.getUserId().equals(userId))
                .count();
    }

    @Override
    public void clean(User user) {
        int count = 0;
        List<Basket> basketList = Database.baskets
                .stream()
                .filter(basket -> basket.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
        for (Basket basket : basketList) {
            Database.baskets.remove(basket);
            count++;
        }
        logger.info(count + " rows was deleted from basket");
    }
}
