package com.service;

import com.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void addUser(String email, String login, String password);

    void removeUser(User user);

    Map<String, String> getMapLoginToEmail();

    User getByLogin(String login);

    List<User> getAll();

    User getById(Long id);
}
