package dao;

import model.User;
import service.Database;

import java.util.NoSuchElementException;

public class UserDao implements DaoInterface<User> {

    private static Long id = 0L;

    public User create(String email, String login, String password) {
        if (email == null || login == null || password == null) {
            throw new NoSuchElementException("Wrong arguments!");
        }
        id++;
        return new User(id, email, login, password);
    }

    @Override
    public void add(User item) {
        Database.users.add(item);
    }
}
