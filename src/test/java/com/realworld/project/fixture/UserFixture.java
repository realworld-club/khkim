package com.realworld.project.fixture;

import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.domain.Users;
import com.realworld.project.user.domain.UsersRepository;
import com.realworld.project.user.service.UserService;

import java.util.Optional;

public class UserFixture {
    public static final String username = "test";
    public static final String email = "test@test.com";
    public static final String password = "123456789";

    public static void register(UserService userService) {
        RequestRegisterUser requestRegisterUser = new RequestRegisterUser(username, email, password);
        userService.registerUsers(requestRegisterUser);
    }

    public static Users getUser(UsersRepository usersRepository, String email) {
        Optional<Users> byEmail = usersRepository.findByEmail(email);
        return byEmail.get();
    }
}
