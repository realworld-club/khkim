package com.realworld.project.user.api;

import com.realworld.project.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserModel register(@RequestBody UserRegisterRequest request) {

        return userService.register(request.toEntity());
    }

    @PostMapping("/users/login")
    public UserModel login(@RequestBody UserLoginRequest request) {

        return null;
    }


}
