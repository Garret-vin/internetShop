package service;

import model.User;

import java.util.List;

public interface UserService {

    User create(String email, String login, String password);

    void add(User item);

    List<User> getAll();
}
