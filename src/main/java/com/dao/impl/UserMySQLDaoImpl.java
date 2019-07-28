package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserMySQLDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserMySQLDaoImpl.class);
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String GET_LOGIN_TO_EMAIL = "SELECT login, email FROM users";
    private static final String ADD_USER = "INSERT INTO users (login, email, password, role) " +
            "VALUES (?, ?, ?, ?)";

    private static final String UPDATE_USER = "UPDATE users SET login = ?, email = ?, " +
            "password = ?, role = ? WHERE id = ?";

    @Override
    public void add(User user) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.execute();

            logger.info(user + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add user was failed", e);
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            int rows = statement.executeUpdate();
            logger.info(rows + " row in table users was deleted");
        } catch (SQLException e) {
            logger.error("Try to remove user was failed", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setLong(5, user.getId());
            int columns = statement.executeUpdate();
            logger.info(columns + " columns was updated");
        } catch (SQLException e) {
            logger.error("Try to update user was failed", e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

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
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

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
    public Map<String, String> getMapLoginToEmail() {
        Map<String, String> loginToEmailMap = new HashMap<>();
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_LOGIN_TO_EMAIL);

            while (resultSet.next()) {
                loginToEmailMap.put(
                        resultSet.getString("login"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            logger.error("Try to get login-to-email map was failed", e);
        }
        return loginToEmailMap;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);

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
