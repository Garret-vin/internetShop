package com.factory;

import com.dao.CodeDao;
import com.dao.impl.CodeMySQLDaoImpl;

public class CodeDaoFactory {

    private static CodeDao instance;

    private CodeDaoFactory() {
    }

    public static CodeDao getInstance() {
        if (instance == null) {
            instance = new CodeMySQLDaoImpl();
        }
        return instance;
    }
}
