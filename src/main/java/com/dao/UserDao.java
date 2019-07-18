package com.dao;

import com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    void remove(Long id);

    void update(User oldUser, User newUser);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    List<User> getAll();
}
