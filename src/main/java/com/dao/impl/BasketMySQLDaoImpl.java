package com.dao.impl;

import com.dao.BasketDao;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasketMySQLDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketMySQLDaoImpl.class);

    @Override
    public void addProduct(Long userId, Long productId) {
        String query = String.format("INSERT INTO basket (user_id, product_id) VALUES (%d, %d)",
                userId, productId);
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size(Long userId) {
        String query = "SELECT COUNT(*) FROM basket WHERE user_id = " + userId;
        int count = 0;
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Try to get size of basket was failed", e);
        }
        return count;
    }

    @Override
    public void clean(User user) {
        String query = "DELETE FROM basket WHERE user_id = " + user.getId();
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate(query);
            logger.info(rows + " rows was deleted from basket");
        } catch (SQLException e) {
            logger.error("Try to clean basket was failed", e);
        }
    }
}
