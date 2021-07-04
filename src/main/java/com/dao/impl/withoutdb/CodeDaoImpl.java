package com.dao.impl.withoutdb;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import com.utils.Database;
import com.utils.IdGeneratorUtil;
import org.apache.log4j.Logger;

import java.util.Optional;

public class CodeDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeDaoImpl.class);

    @Override
    public void add(Code code) {
        code.setId(IdGeneratorUtil.getCodeId());
        Database.codes.add(code);
        logger.info(code + " was added to DB");
    }

    @Override
    public Optional<Code> getById(Long id) {
        return Database.codes
                .stream()
                .filter(code -> code.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        return Database.codes
                .stream()
                .filter(code -> code.getUser().equals(user))
                .min((c1, c2) -> (int) (c2.getId() - c1.getId()));
    }
}
