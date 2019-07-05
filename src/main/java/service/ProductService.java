package service;

import model.Product;

import java.util.List;

public interface ProductService {

    Product create(String name, String description, Double price);

    void add(Product item);

    List<Product> getAll();
}
