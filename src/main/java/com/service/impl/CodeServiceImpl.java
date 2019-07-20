package com.service.impl;

import com.dao.CodeDao;
import com.factory.CodeDaoFactory;
import com.model.Code;
import com.model.User;
import com.service.CodeService;

import java.util.Optional;

public class CodeServiceImpl implements CodeService {

    private static final CodeDao codeDao = CodeDaoFactory.getInstance();

    @Override
    public void add(Code code) {
        codeDao.add(code);
    }

    @Override
    public Optional<Code> getById(Long id) {
        return codeDao.getById(id);
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        return codeDao.getLastCodeForUser(user);
    }
}
