package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public User create(String email, String login, String password) {
        return userDao.create(email, login, password);
    }

    @Override
    public void add(User item) {
        userDao.add(item);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
