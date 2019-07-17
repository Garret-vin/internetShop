package com.dao.impl;

import com.dao.ProductDao;
import com.model.Product;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public void add(Product product) {
        product.setId(IdGeneratorUtil.getProductId());
        Database.products.add(product);
        logger.info("Product " + product + " was added in system.");
    }

    @Override
    public void remove(Long id) {
        Optional<Product> optionalProduct = getById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Database.products.remove(product);
            logger.info(product + " was deleted from database");
        } else {
            logger.warn("Can't delete product. Reason: product not found!");
        }
    }

    @Override
    public void update(Product oldProduct, Product newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setPrice(newProduct.getPrice());
        logger.info(oldProduct + " was updated");
    }

    @Override
    public List<Product> getAll() {
        return Database.products;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Database.products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
}
