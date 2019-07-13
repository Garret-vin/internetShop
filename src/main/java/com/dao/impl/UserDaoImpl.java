package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.utils.Database;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void add(User user) {
        Database.users.add(user);
        logger.info("User " + user + " was added in system.");
    }

    @Override
    public void remove(Long id) {
        Optional<User> optionalUser = getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Database.users.remove(user);
            logger.info(user + " was deleted from database");
        } else {
            logger.warn("Can't delete user. Reason: user not found!");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        List<User> allUsers = getAll();
        return allUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        List<User> allUsers = getAll();
        return allUsers.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Database.users;
    }
}
