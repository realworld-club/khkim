package com.realworld.project.application.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordHelper(PasswordEncoder passwordEncoder) {
        PasswordHelper.passwordEncoder = passwordEncoder;
    }

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
