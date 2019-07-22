package com.dao.impl;

import com.dao.BasketDao;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketMySQLDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketMySQLDaoImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO basket (user_id, product_id) VALUES (?, ?)";
    private static final String GET_SIZE = "SELECT COUNT(*) FROM basket WHERE user_id = ?";
    private static final String CLEAN_BASKET = "DELETE FROM basket WHERE user_id = ?";

    @Override
    public void addProduct(Long userId, Long productId) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setLong(1, userId);
            statement.setLong(2, productId);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Try to add product in basket failed!", e);
        }
    }

    @Override
    public int size(Long userId) {
        int count = 0;
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_SIZE)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
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
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(CLEAN_BASKET)) {
            statement.setLong(1, user.getId());
            int rows = statement.executeUpdate();
            logger.info(rows + " rows was deleted from basket");
        } catch (SQLException e) {
            logger.error("Try to clean basket was failed", e);
        }
    }
}
