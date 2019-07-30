package com.dao.impl;

import com.dao.ProductDao;
import com.model.Product;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMySQLDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductMySQLDaoImpl.class);
    private static final String DELETE_USER = "DELETE FROM products WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM products";
    private static final String ADD_PRODUCT = "INSERT INTO products (name, description, price) " +
            "VALUES (?, ?, ?)";

    private static final String UPDATE_PRODUCT = "UPDATE products SET " +
            "name = ?, description = ?, price = ? WHERE id = ?";

    @Override
    public void add(Product product) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.execute();
            logger.info(product + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add product was failed", e);
        }
    }

    @Override
    public void remove(Product product) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setLong(1, product.getId());
            int rows = statement.executeUpdate();
            logger.info(rows + " row in table products was deleted");
        } catch (SQLException e) {
            logger.error("Try to remove product was failed", e);
        }
    }

    @Override
    public void update(Long productId, Product product) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setLong(4, productId);
            int columns = statement.executeUpdate();
            logger.info(columns + " columns was updated");
        } catch (SQLException e) {
            logger.error("Try to update product was failed", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();

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
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            logger.error("Try to get product by id was failed", e);
        }
        return Optional.empty();
    }
}
