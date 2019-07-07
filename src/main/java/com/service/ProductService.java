package com.service;

import com.model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(String name, String description, Double price);

    List<Product> getAll();
}
