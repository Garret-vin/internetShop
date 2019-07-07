package com.service.impl;

import com.dao.UserDao;
import com.factory.UserDaoFactory;
import com.model.User;
import com.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public void addUser(String email, String login, String password) {
        User user = userDao.create(email, login, password);
        userDao.add(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
