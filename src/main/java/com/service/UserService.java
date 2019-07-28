package com.service;

import com.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    void add(User user);

    List<User> getAll();

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    Map<String, String> getMapLoginToEmail();

    void update(User user);

    void remove(Long id);
}
