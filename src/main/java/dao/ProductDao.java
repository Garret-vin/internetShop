package dao;

import model.Product;
import service.Database;
import java.util.NoSuchElementException;

public class ProductDao implements DaoInterface<Product> {

    private static Long id = 0L;

    public Product create(String name, String description, Double price) {
        if (name == null || description == null || price == null) {
            throw new NoSuchElementException("Wrong arguments!");
        }
        id++;
        return new Product(id, name, description, price);
    }

    @Override
    public void add(Product item) {
        Database.products.add(item);
    }
}
