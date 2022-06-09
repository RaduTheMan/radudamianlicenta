package com.org.example;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1;

public class PasswordUtil {

    private final Pbkdf2PasswordEncoder encoder;
    private static PasswordUtil instance = null;

    private PasswordUtil() {
        encoder = new Pbkdf2PasswordEncoder();
        encoder.setAlgorithm(PBKDF2WithHmacSHA1);
    }

    public static PasswordUtil getInstance() {
        if (instance == null)
            instance = new PasswordUtil();
        return instance;
    }

    public String generateHashedPassword (String originalPassword) {
        return encoder.encode(originalPassword);
    }
}
