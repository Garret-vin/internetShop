package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMySQLDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserMySQLDaoImpl.class);

    @Override
    public void add(User user) {
        String query = String.format("INSERT INTO users (login, email, password, role) " +
                        "VALUES ('%s', '%s', '%s', '%s')",
                user.getLogin(), user.getEmail(), user.getPassword(), user.getRole());
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            logger.info(user + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add user was failed", e);
        }
    }

    @Override
    public void remove(Long id) {
        String query = String.format("DELETE FROM users WHERE id = %d", id);
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate(query);
            logger.info(rows + " row in table users was deleted");
        } catch (SQLException e) {
            logger.error("Try to remove user was failed", e);
        }
    }

    @Override
    public void update(User user) {
        String query = String.format("UPDATE users SET " +
                        "login = '%s', " +
                        "email = '%s', " +
                        "password = '%s', " +
                        "role = '%s' " +
                        "WHERE id = %d",
                user.getLogin(), user.getEmail(), user.getPassword(),
                user.getRole(), user.getId());
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info(user + " was updated");
        } catch (SQLException e) {
            logger.error("Try to update user was failed", e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        String query = String.format("SELECT * FROM users WHERE id = %d", id);
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("Try to get by id user was failed", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        String query = String.format("SELECT * FROM users WHERE login = '%s'", login);
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("Try to get by login user was failed", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                userList.add(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("Try to get all users was failed", e);
        }
        return userList;
    }
}
