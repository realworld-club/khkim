package com.realworld.project.fixture;

import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.application.user.service.CredentialService;

import java.util.Optional;

public class UserFixture {
    public static final String username = "test";
    public static final String email = "test@test.com";
    public static final String password = "123456789";
    public static final String bio = "test-company";
    public static final String image = "test.jpg";

    public static final String usernameA = "test-A";
    public static final String emailA = "test-A@test.com";
    public static final String passwordA = "987654321";
    public static final String bioA = "test-A-company";
    public static final String imageA = "test-A.jpg";

    public static final String usernameB = "test-B";
    public static final String emailB = "test-B@test.com";
    public static final String passwordB = "123789456";
    public static final String bioB = "test-B-company";
    public static final String imageB = "test-B.jpg";

    public static void register_user(CredentialService credentialService) {
        RequestRegisterUser requestRegisterUser = new RequestRegisterUser(username, email, password);
        credentialService.registerUsers(requestRegisterUser);
    }
    public static void register_userA(CredentialService credentialService) {
        RequestRegisterUser requestRegisterUser = new RequestRegisterUser(usernameA, emailA, passwordA);
        credentialService.registerUsers(requestRegisterUser);
    }

    public static User getUser(UserRepository userRepository, String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.get();
    }
}
