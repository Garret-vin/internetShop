package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

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
    public void add(User item) {
        Database.users.add(item);
    }

    @Override
    public List<User> getAll() {
        return Database.users;
    }
}
