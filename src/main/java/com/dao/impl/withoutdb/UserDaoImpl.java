package com.dao.impl.withoutdb;

import com.dao.UserDao;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void add(User user) {
        user.setId(IdGeneratorUtil.getUserId());
        Database.users.add(user);
        logger.info("User " + user + " was added in system.");
    }

    @Override
    public void remove(User user) {
        Database.users.remove(user);
        logger.info(user + " was deleted from database");
    }

    @Override
    public void update(Long userId, User user) {
        Optional<User> oldUserOptional = getById(userId);
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
    public List<User> getAll() {
        return Database.users;
    }

    @Override
    public Optional<User> getByLoginOrEmail(String login, String email) {
        return Database.users.stream()
                .filter(user -> user.getLogin().equals(login) || user.getEmail().equals(email))
                .findFirst();
    }
}
