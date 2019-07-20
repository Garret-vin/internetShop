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
    public void addProduct(Product product) {
        productDao.add(product);
    }

    @Override
    public void remove(Long id) {
        productDao.remove(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
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
