package com.service;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(Product product);

    void remove(Long id);

    void update(Product oldProduct, Product newProduct);

    List<Product> getAll();

    Optional<Product> getById(Long id);
}
