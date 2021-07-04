package com.dao.impl.withoutdb;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.Optional;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    @Override
    public void add(Basket basket) {
        basket.setId(IdGeneratorUtil.getBasketId());
        Database.baskets.add(basket);
        logger.info(basket + " was added to DB");
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        basket.getProductList().add(product);
    }

    @Override
    public int size(Basket basket) {
        return basket.getProductList().size();
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        return Database.baskets
                .stream()
                .filter(basket -> basket.getUser().equals(user))
                .max((b1, b2) -> (int) (b1.getId() - b2.getId()));
    }
}
