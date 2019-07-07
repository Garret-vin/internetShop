package com.service;

import com.model.User;

import java.util.List;

public interface UserService {

    void addUser(String email, String login, String password);

    List<User> getAll();
}
