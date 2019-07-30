package com.service.impl;

import com.dao.ProductDao;
import com.factory.ProductDaoFactory;
import com.model.Product;
import com.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void remove(Product product) {
        productDao.remove(product);
    }

    @Override
    public void update(Long productId, Product product) {
        productDao.update(productId, product);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }
}
