package com.factory;

import com.service.CodeService;
import com.service.impl.CodeServiceImpl;

public class CodeServiceFactory {

    private static CodeService instance;

    private CodeServiceFactory() {
    }

    public static CodeService getInstance() {
        if (instance == null) {
            instance = new CodeServiceImpl();
        }
        return instance;
    }
}
