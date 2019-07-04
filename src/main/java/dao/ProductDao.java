package dao;

import model.Product;
import service.Database;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductDao implements DaoInterface<Product> {

    private static Long id = 0L;

    public static Product create(String name, String description, Double price) {
        if (Objects.isNull(name)
                || Objects.isNull(description)
                || Objects.isNull(price)) {
            throw new NoSuchElementException("Wrong arguments!");
        }
        id++;
        return new Product(id, name, description, price);
    }

    @Override
    public void add(Product item) {
        Database.products.add(item);
    }

    @Override
    public List<Product> getAll() {
        return Database.products;
    }
}
