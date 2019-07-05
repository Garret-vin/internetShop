package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public Product create(String name, String description, Double price) {
        return productDao.create(name, description, price);
    }

    @Override
    public void add(Product item) {
        productDao.add(item);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
