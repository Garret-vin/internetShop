package com.dao.impl.mysql;

import com.dao.BasketDao;
import com.dao.OrderDao;
import com.factory.BasketDaoFactory;
import com.model.Basket;
import com.model.Code;
import com.model.Order;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderMySQLDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderMySQLDaoImpl.class);
    private static final BasketDao basketDao = BasketDaoFactory.getInstance();
    private static final String ADD_ORDER = "INSERT INTO orders " +
            "(basket_id, user_id, code_id, email, phone_number, address) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_BY_ID = "SELECT orders.id, basket_id, orders.user_id, code_id, " +
            "orders.email, phone_number, address, login, password, role, value " +
            "FROM orders INNER JOIN users ON orders.user_id = users.id " +
            "INNER JOIN code ON orders.code_id = code.id WHERE orders.id = ?";

    private static final String GET_LAST_ORDER = "SELECT orders.id, basket_id, orders.user_id, " +
            "code_id, email, phone_number, address, value " +
            "FROM orders INNER JOIN code ON orders.code_id = code.id " +
            "WHERE orders.user_id = ? ORDER BY orders.id DESC LIMIT 1";

    @Override
    public void add(Order order) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_ORDER)) {
            statement.setLong(1, order.getBasket().getId());
            statement.setLong(2, order.getUser().getId());
            statement.setLong(3, order.getCode().getId());
            statement.setString(4, order.getEmail());
            statement.setString(5, order.getPhoneNumber());
            statement.setString(6, order.getAddress());
            statement.execute();
            logger.info(order + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add order was failed!", e);
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                Optional<Basket> optionalBasket = basketDao.getBasketByUser(user);
                Basket basket = null;
                if (optionalBasket.isPresent()) {
                    basket = optionalBasket.get();
                }
                Code code = new Code(
                        resultSet.getLong("code_id"),
                        resultSet.getString("value"),
                        user);
                Order order = new Order(
                        resultSet.getLong("id"),
                        basket,
                        user,
                        code,
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"));

                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.error("Try to get order by id was failed!", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getLastOrderForUser(User user) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_LAST_ORDER)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Optional<Basket> optionalBasket = basketDao.getBasketByUser(user);
                Basket basket = null;
                if (optionalBasket.isPresent()) {
                    basket = optionalBasket.get();
                }
                Code code = new Code(
                        resultSet.getLong("code_id"),
                        resultSet.getString("value"),
                        user);
                Order order = new Order(
                        resultSet.getLong("id"),
                        basket,
                        user,
                        code,
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"));

                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.error("Try to get last order for user was failed", e);
        }
        return Optional.empty();
    }
}
