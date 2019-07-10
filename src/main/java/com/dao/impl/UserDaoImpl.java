package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User create(String email, String login, String password) {
        if (Objects.isNull(email)
                || Objects.isNull(login)
                || Objects.isNull(password)) {
            throw new NoSuchElementException("Wrong arguments!");
        }
        return new User(IdGeneratorUtil.getUserId(), email, login, password);
    }

    @Override
    public void add(String email, String login, String password) {
        User user = create(email, login, password);
        Database.users.add(user);
        logger.info("User " + user + " was added in system.");
    }

    @Override
    public void remove(Long id) {
        User user = getById(id);
        boolean result = Database.users.remove(user);
        logger.info(user + " removing result -> " + result);
    }

    @Override
    public User getById(Long id) {
        for (User user : Database.users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        for (User user : Database.users) {
            if (login.equals(user.getLogin())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return Database.users;
    }
}
