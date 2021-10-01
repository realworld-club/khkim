package com.realworld.project.fixture;

import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.service.UserService;

public class UserFixture {
    public static final String username = "test";
    public static final String email = "test@test.com";
    public static final String password = "123456789";

    public static void register(UserService userService) {
        RequestRegisterUser requestRegisterUser = new RequestRegisterUser(username, email, password);
        userService.registerUsers(requestRegisterUser);
    }
}
