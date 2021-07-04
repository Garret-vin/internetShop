package com.dao.impl.mysql;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketMySQLDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketMySQLDaoImpl.class);
    private static final String ADD_BASKET = "INSERT INTO basket (user_id) VALUE (?)";
    private static final String GET_SIZE = "SELECT COUNT(*) FROM product_basket WHERE basket_id = ?";
    private static final String ADD_PRODUCT = "INSERT INTO product_basket (basket_id, product_id)" +
            " VALUES (?, ?)";

    private static final String GET_BASKET_BY_USER_ID = "SELECT id, user_id FROM basket " +
            "WHERE user_id = ? ORDER BY id DESC LIMIT 1";

    private static final String GET_PRODUCTS_FROM_BASKET = "SELECT products.id, name, description, price " +
            "FROM products INNER JOIN product_basket ON products.id = product_basket.product_id " +
            "WHERE basket_id = ?";

    @Override
    public void add(Basket basket) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_BASKET)) {
            statement.setLong(1, basket.getUser().getId());
            statement.execute();
            logger.info(basket + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add basket failed!", e);
        }
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setLong(1, basket.getId());
            statement.setLong(2, product.getId());
            statement.execute();
            logger.info(product + " was added to basket " + basket);
        } catch (SQLException e) {
            logger.error("Try to add product in basket failed!", e);
        }
    }

    @Override
    public int size(Basket basket) {
        int count = 0;
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_SIZE)) {
            statement.setLong(1, basket.getId());
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
    public Optional<Basket> getBasketByUser(User user) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_BASKET_BY_USER_ID)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                List<Product> productList = getProductListByBasketId(resultSet.getLong("id"));
                Basket basketFromDb = new Basket(
                        resultSet.getLong("id"),
                        user,
                        productList);
                return Optional.of(basketFromDb);
            }
        } catch (SQLException e) {
            logger.error("Try to get basket by user id was failed", e);
        }
        return Optional.empty();
    }

    private List<Product> getProductListByBasketId(Long basketId) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_FROM_BASKET)) {
            statement.setLong(1, basketId);
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
            logger.error("Try to get product list by basket id was failed", e);
        }
        return productList;
    }
}
