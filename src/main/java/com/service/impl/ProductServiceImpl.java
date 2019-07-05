package com.service.impl;

import com.dao.ProductDao;
import com.factory.ProductDaoFactory;
import com.model.Product;
import com.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public void addProduct(String name, String description, Double price) {
        Product product = productDao.create(name, description, price);
        productDao.add(product);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
