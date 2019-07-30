package com.service.impl;

import com.dao.UserDao;
import com.factory.UserDaoFactory;
import com.model.User;
import com.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public Optional<User> getByLoginOrEmail(String login, String email) {
        return userDao.getByLoginOrEmail(login, email);
    }

    @Override
    public void update(Long userId, User user) {
        userDao.update(userId, user);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.getById(id);
    }
}
