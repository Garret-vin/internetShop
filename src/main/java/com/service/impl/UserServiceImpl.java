package com.service.impl;

import com.dao.UserDao;
import com.factory.UserDaoFactory;
import com.model.User;
import com.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public void addUser(String email, String login, String password) {
        try {
            userDao.add(email, login, password);
        } catch (NoSuchElementException e) {
            logger.warn("User can't be added, field is null", e);
        }
    }

    @Override
    public void removeUser(User user) {
        userDao.remove(user.getId());
    }

    @Override
    public Map<String, String> getMapLoginToEmail() {
        return userDao.getAll()
                .stream()
                .collect(Collectors.toMap(User::getLogin, User::getEmail));
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
