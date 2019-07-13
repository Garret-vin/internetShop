package com.service.impl;

import com.dao.UserDao;
import com.factory.UserDaoFactory;
import com.model.User;
import com.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public Map<String, String> getMapLoginToEmail() {
        return userDao.getAll()
                .stream()
                .collect(Collectors.toMap(User::getLogin, User::getEmail));
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
