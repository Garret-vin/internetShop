package com.utils;

import com.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class HashUtil {

    private static final int SALT_SIZE = 6;

    public static void saltPassword(User user) {
        byte[] salt = generateSalt();
        String saltedPassword = getSaltedPassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(saltedPassword);
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_SIZE];
        for (int i = 0; i < SALT_SIZE; i++) {
            salt[i] = (byte) new Random().nextInt(10);
        }
        return salt;
    }


    public static String getSaltedPassword(String password, byte[] salt) {
        String start = "";
        String end = "";
        for (int i = 0; i < SALT_SIZE / 2; i++) {
            start += salt[i];
        }
        for (int i = SALT_SIZE / 2; i < SALT_SIZE; i++) {
            end += salt[i];
        }
        start = DigestUtils.sha256Hex(start);
        end = DigestUtils.sha256Hex(end);
        String encryptedPassword = DigestUtils.sha256Hex(password);
        return DigestUtils.sha256Hex(
                start + encryptedPassword + end);
    }
}
