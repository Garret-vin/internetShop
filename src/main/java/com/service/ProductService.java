package com.service;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(Product product);

    void remove(Long id);

    List<Product> getAll();

    Optional<Product> getById(Long id);
}
