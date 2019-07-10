package com.dao.impl;

import com.dao.ProductDao;
import com.model.Product;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public Product create(String name, String description, Double price) {
        if (Objects.isNull(name)
                || Objects.isNull(description)
                || Objects.isNull(price)) {
            throw new NoSuchElementException("Wrong arguments!");
        } else {
            return new Product(IdGeneratorUtil.getProductId(), name, description, price);
        }
    }

    @Override
    public void add(Product product) {
        Database.products.add(product);
        logger.info("Product " + product + " was added in system.");
    }

    @Override
    public void remove(Product product) {
        boolean result = Database.products.remove(product);
        logger.info("Remove of " + product + " = " + result);
    }

    @Override
    public List<Product> getAll() {
        return Database.products;
    }
}
