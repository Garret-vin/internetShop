package com.dao.impl.mysql;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import com.utils.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CodeMySQLDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeMySQLDaoImpl.class);
    private static final String ADD_CODE = "INSERT INTO code (user_id, value) VALUES (?, ?)";
    private static final String GET_BY_ID = "SELECT code.id, user_id, value, login, " +
            "email, password, role, salt FROM code INNER JOIN users ON code.user_id = users.id " +
            "WHERE code.id = ?";

    private static final String GET_LAST_CODE = "SELECT * FROM code WHERE user_id = ? " +
            "ORDER BY id DESC LIMIT 1";

    @Override
    public void add(Code code) {
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(ADD_CODE)) {
            statement.setLong(1, code.getUser().getId());
            statement.setString(2, code.getValue());
            statement.execute();
            logger.info(code + " was added to DB");
        } catch (SQLException e) {
            logger.error("Try to add code was failed", e);
        }
    }

    @Override
    public Optional<Code> getById(Long id) {
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
                        resultSet.getString("role"),
                        resultSet.getBytes("salt"));
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
        try (Connection connection = DBConnector.connect();
             PreparedStatement statement = connection.prepareStatement(GET_LAST_CODE)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

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
