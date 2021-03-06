package com.spyapp.server.util;


import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;

public class RandomStringGenerator {

    private static RandomStringGenerator generator;
    private final SecureRandom random;
    private final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    private RandomStringGenerator() {
        random = new SecureRandom();

    }

    public synchronized static RandomStringGenerator getInstance() {

        if (generator == null) {
            generator = new RandomStringGenerator();
        }
        return generator;
    }

    public String getString(int len) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < len; i++) {

            int randomNum = random.nextInt(alphabet.length());
            stringBuilder.append(alphabet.charAt(randomNum));

        }

        return stringBuilder.toString();
    }

    public String getHashID(int i) {
        String normalString = getString(i);
        String hashed = BCrypt.hashpw(normalString, BCrypt.gensalt(12));

        return hashed;
    }

}