package com.dao.impl;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class CodeMySQLDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeMySQLDaoImpl.class);

    @Override
    public void add(Code code) {
        String query = String.format("INSERT INTO code (user_id, value) VALUES (%d, '%s')",
                code.getUser().getId(), code.getValue());
        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            logger.info(code + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add code was failed", e);
        }
    }

    @Override
    public Optional<Code> getById(Long id) {
        String query = "SELECT code.id, user_id, value, login, email, password, role " +
                "FROM code INNER JOIN users ON code.user_id = users.id WHERE code.id = " + id;

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
                        resultSet.getLong("id"),
                        resultSet.getString("value"),
                        user);
                return Optional.of(code);
            }
        } catch (SQLException e) {
            logger.error("Try to get code by id was failed", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        String query = String.format("SELECT * FROM code WHERE user_id = %d " +
                "ORDER BY id DESC LIMIT 1", user.getId());

        try (Connection connection = DBConnector.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Code code = new Code(
                        resultSet.getLong("id"),
                        resultSet.getString("value"),
                        user);
                return Optional.of(code);
            }
        } catch (SQLException e) {
            logger.error("Try to get last code for user was failed", e);
        }
        return Optional.empty();
    }
}
