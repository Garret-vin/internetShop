package com.service.impl;

import com.dao.ProductDao;
import com.factory.ProductDaoFactory;
import com.model.Product;
import com.service.ProductService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public void addProduct(String name, String description, Double price) {
        try {
            Product product = productDao.create(name, description, price);
            productDao.add(product);
        } catch (NoSuchElementException e) {
            logger.warn("Product can't be added, field is null", e);
        }
    }

    @Override
    public void remove(Product product) {
        productDao.remove(product);
    }

    public Map<Long, Product> getMapIdToProduct() {
        return productDao.getAll().stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product getById(Long id) {
        for (Product product : getAll()) {
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }
}
