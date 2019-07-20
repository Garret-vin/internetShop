package com.dao.impl;

import com.dao.ProductDao;
import com.model.Product;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMySQLDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductMySQLDaoImpl.class);

    @Override
    public void add(Product product) {
        String query = String.format("INSERT INTO products (name, description, price) " +
                        "VALUES ('%s', '%s', " + product.getPrice() + ")",
                product.getName(), product.getDescription());
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            logger.info(product + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add product was failed", e);
        }
    }

    @Override
    public void remove(Long id) {
        String query = String.format("DELETE FROM products WHERE id = %d", id);
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate(query);
            logger.info(rows + " row in table products was deleted");
        } catch (SQLException e) {
            logger.error("Try to remove product was failed", e);
        }
    }

    @Override
    public void update(Product product) {
        String query = String.format("UPDATE products SET " +
                        "name = '%s', description = '%s', price = " + product.getPrice(),
                product.getName(), product.getDescription());
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info(product + " was updated");
        } catch (SQLException e) {
            logger.error("Try to update product was failed", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            logger.error("Try to get all products was failed", e);
        }
        return productList;
    }

    @Override
    public Optional<Product> getById(Long id) {
        String query = String.format("SELECT * FROM products WHERE id = %d", id);
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
