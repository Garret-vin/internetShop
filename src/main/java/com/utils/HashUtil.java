package com.utils;

import com.model.User;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {

    private static final Logger logger = Logger.getLogger(HashUtil.class);

    public static void saltPassword(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String saltedPassword = getSaltedPassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(saltedPassword);
    }

    public static String getSaltedPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            for (byte b : hashBytes) {
                hashPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can't find algorithm for hashing", e);
        }
        return hashPassword.toString();
    }
}
