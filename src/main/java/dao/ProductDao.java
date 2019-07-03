package dao;

import model.Product;
import service.Database;

public class ProductDao implements DaoInterface<Product> {

    private static Long id = 0L;

    public Product create(String name, String description, Double price) {
        id++;
        return new Product(id, name, description, price);
    }

    @Override
    public void add(Product item) {
        Database.products.add(item);
    }
}
