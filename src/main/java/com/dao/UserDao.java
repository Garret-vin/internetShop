package com.dao;

import com.model.User;

import java.util.List;

public interface UserDao {

    User create(String email, String login, String password);

    void add(String email, String login, String password);

    void remove(User user);

    User getById(Long id);

    User getByLogin(String login);

    List<User> getAll();
}
