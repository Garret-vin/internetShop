package dao;

import model.User;
import service.Database;

public class UserDao implements DaoInterface<User> {

    private static Long id = 0L;

    public User create(String email, String login, String password) {
        id++;
        return new User(id, email, login, password);
    }

    @Override
    public void add(User item) {
        Database.users.add(item);
    }
}
