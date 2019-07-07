package com.dao;

import com.model.User;

import java.util.List;

public interface UserDao {

    User create(String email, String login, String password);

    void add(User item);

    List<User> getAll();
}
