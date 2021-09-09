package com.realworld.project.fixture;

import com.realworld.project.user.api.dto.UserLoginRequest;
import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.api.dto.UserRegisterRequest;
import com.realworld.project.user.api.dto.UserUpdateRequest;
import com.realworld.project.user.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserFixture {
    public static final String userPk = "40";
    public static final String username = "test-username";
    public static final String email = "test-email@email.com";
    public static final String password = "test-password";
    public static final String bio = "test-bio";
    public static final String image = "test-image.jpg";

    public static final String new_username = "new test-username";
    public static final String new_email = "new test-email@email.com";
    public static final String new_password = "new test-password";
    public static final String new_bio = "new test-bio";
    public static final String new_image = "new test-image.jpg";

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    public static final String token = JwtFixture.crateToken(userPk);


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
                new_email,
                new_password,
                new_username,
                new_bio,
                new_image
        );
    }

    public static UserModel ofModel() {
        return new UserModel(
                email,
                token,
                username,
                bio,
                image
        );
    }

    public static User ofEntity() {
        return new User(
                username,
                encoder.encode(password),
                email,
                token,
                bio,
                image,
                false
        );
    }

    public static User ofNewEntity() {
        return new User(
                new_username,
                encoder.encode(new_password),
                new_email,
                token,
                new_bio,
                new_image,
                false
        );
    }

    public static String getToken() {
        return token;
    }
}
