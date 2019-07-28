package com.dao;

import com.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    void remove(User user);

    void update(User user);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    Map<String, String> getMapLoginToEmail();

    List<User> getAll();
}
