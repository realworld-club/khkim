package com.realworld.project.user.api;

import com.realworld.project.user.api.wrapper.UserModelWrapper;
import com.realworld.project.user.application.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/login")
    public UserModelWrapper login(@RequestBody UserLoginRequest request) {
        return new UserModelWrapper(userFacade.login(request));
    }


}
