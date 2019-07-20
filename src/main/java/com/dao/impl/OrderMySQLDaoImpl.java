package com.dao.impl;

import com.dao.OrderDao;
import com.model.Code;
import com.model.Order;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class OrderMySQLDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderMySQLDaoImpl.class);

    @Override
    public void add(Order order) {
        String query = String.format("INSERT INTO orders " +
                        "(user_id, code_id, email, phone_number, address) " +
                        "VALUES (%d, %d, '%s', '%s', '%s')",
                order.getUser().getId(), order.getCode().getId(),
                order.getEmail(), order.getPhoneNumber(), order.getAddress());

        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            logger.info(order + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add order was failed!", e);
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        String query = "SELECT orders.id, orders.user_id, code_id, orders.email, " +
                "phone_number, address, login, password, role, value " +
                "FROM orders INNER JOIN users ON orders.user_id = users.id " +
                "INNER JOIN code ON orders.code_id = code.id WHERE orders.id = " + id;

        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                Code code = new Code(
                        resultSet.getLong("code_id"),
                        resultSet.getString("value"),
                        user);
                Order order = new Order(
                        resultSet.getLong("id"),
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
        String query = String.format("SELECT orders.id, orders.user_id, code_id, " +
                "email, phone_number, address, value " +
                "FROM orders INNER JOIN code ON orders.code_id = code.id " +
                "WHERE orders.user_id = %d ORDER BY orders.id DESC LIMIT 1", user.getId());

        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Code code = new Code(
                        resultSet.getLong("code_id"),
                        resultSet.getString("value"),
                        user);
                Order order = new Order(
                        resultSet.getLong("id"),
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
