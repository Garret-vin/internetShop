package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void add(User user) {
        user.setId(IdGeneratorUtil.getUserId());
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
    public void update(User user) {
        Optional<User> oldUserOptional = getById(user.getId());
        if (oldUserOptional.isPresent()) {
            User oldUser = oldUserOptional.get();
            oldUser.setLogin(user.getLogin());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            logger.info(oldUser + " was updated");
        } else {
            logger.warn("Updating failed! Reason: user not found!");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        return Database.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Database.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Map<String, String> getMapLoginToEmail() {
        return Database.users
                .stream()
                .collect(Collectors.toMap(User::getLogin, User::getEmail));
    }

    @Override
    public List<User> getAll() {
        return Database.users;
    }
}
