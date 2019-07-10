package com.service;

import com.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void addProduct(String name, String description, Double price);

    void remove(Product product);

    Map<Long, Product> getMapIdToProduct();

    List<Product> getAll();

    Product getById(Long id);
}
