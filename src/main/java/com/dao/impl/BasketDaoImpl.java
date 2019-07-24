package com.dao.impl;

import com.dao.BasketDao;
import com.dao.ProductDao;
import com.factory.ProductDaoFactory;
import com.model.Basket;
import com.model.Product;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.Optional;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public void add(Basket basket) {
        basket.setId(IdGeneratorUtil.getBasketId());
        Database.baskets.add(basket);
        logger.info(basket + " was added to DB");
    }

    @Override
    public void addProduct(Long basketId, Long productId) {
        Optional<Product> optionalProduct = productDao.getById(productId);
        Product product = null;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }

        Optional<Basket> optionalBasket = Database.baskets
                .stream()
                .filter(basket -> basket.getId().equals(basketId))
                .findFirst();

        if (optionalBasket.isPresent()) {
            optionalBasket.get().getProductList().add(product);
        }
    }

    @Override
    public int size(Long userId) {
        return (int) Database.baskets
                .stream()
                .filter(basket -> basket.getUserId().equals(userId))
                .count();
    }

    @Override
    public Optional<Basket> getBasketByUserId(Long userId) {
        return Database.baskets
                .stream()
                .filter(basket -> basket.getUserId().equals(userId))
                .max((b1, b2) -> (int) (b1.getId() - b2.getId()));
    }
}
