package com.realworld.project.fixture;

import com.realworld.project.user.api.UserLoginRequest;
import com.realworld.project.user.api.UserModel;
import com.realworld.project.user.api.UserRegisterRequest;
import com.realworld.project.user.api.UserUpdateRequest;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UserFixture {
    public static final String userPk = "40";
    public static final String username = "test-username";
    public static final String email = "test-email@email.com";
    public static final String password = "test-password";
    public static final String bio = "test-bio";
    public static final String image = "test-image";
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static UserLoginRequest ofLoginRequest() {
        return new UserLoginRequest(email, password);
    }

    public static UserRegisterRequest ofRegisterRequest() {
        return new UserRegisterRequest(email, password, username);
    }

    public static String encodedPassword(String password) {
        return encoder.encode(password);
    }

    public static UserUpdateRequest ofUpdateRequest() {
        return new UserUpdateRequest(
                "new " + email,
                "new " + password,
                "new " + username,
                "new " + bio,
                "new " + image
        );
    }

    public static UserModel ofModel() {
        String token = JwtFixture.crateToken(userPk);
        return new UserModel(
                email,
                token,
                username,
                bio,
                image
        );
    }

    public static User ofEntity() {
        Profile profile = new Profile(bio, image, false);

        return new User(
                username,
                encoder.encode(password),
                email,
                JwtFixture.crateToken(userPk),
                profile
        );
    }

}
