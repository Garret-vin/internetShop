package dao.impl;

import dao.UserDao;
import model.User;
import utils.Database;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    private static Long id = 0L;

    @Override
    public User create(String email, String login, String password) {
        if (Objects.isNull(email)
                || Objects.isNull(login)
                || Objects.isNull(password)) {
            throw new NoSuchElementException("Wrong arguments!");
        }
        id++;
        return new User(id, email, login, password);
    }

    @Override
    public void add(User item) {
        Database.users.add(item);
    }

    @Override
    public List<User> getAll() {
        return Database.users;
    }
}
