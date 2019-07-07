package com.dao;

import com.model.Product;

import java.util.List;

public interface ProductDao {

    Product create(String name, String description, Double price);

    void add(Product item);

    List<Product> getAll();
}
