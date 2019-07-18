package com.service;

import com.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    void add(User user);

    void remove(Long id);

    void update(User oldUser, User newUser);

    Map<String, String> getMapLoginToEmail();

    Optional<User> getByLogin(String login);

    List<User> getAll();

    Optional<User> getById(Long id);
}
