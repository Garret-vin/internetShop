package com.factory;

import com.dao.UserDao;
import com.dao.impl.UserMySQLDaoImpl;

public class UserDaoFactory {

    private static UserDao instance;

    private UserDaoFactory() {
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserMySQLDaoImpl();
        }
        return instance;
    }
}
