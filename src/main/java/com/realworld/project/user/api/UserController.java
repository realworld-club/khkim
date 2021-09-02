package com.realworld.project.user.api;

import com.google.common.base.Preconditions;
import com.realworld.project.user.api.wrapper.UserModelWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserLoginRequestWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserRegisterRequestWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserUpdateRequestWrapper;
import com.realworld.project.user.application.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.google.common.base.Preconditions.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/login")
    public UserModelWrapper<UserModel> login(@RequestBody UserLoginRequestWrapper request) {
        return new UserModelWrapper<>(userFacade.login(request.getContent()));
    }

    @PostMapping
    public UserModelWrapper<UserModel> register(@RequestBody UserRegisterRequestWrapper request) {
        return new UserModelWrapper<>(userFacade.register(request.getContent()));
    }

    @GetMapping
    public UserModelWrapper<UserModel> getCurrentUser(Principal principal){
        return new UserModelWrapper<>(userFacade.getCurrentUser(principal.getName()));
    }

    @PutMapping
    public UserModelWrapper<UserModel> updateUser(@RequestBody UserUpdateRequestWrapper request, Principal principal){
        checkNotNull(principal.getName());

        return new UserModelWrapper<>(userFacade.update(request.getContent(), principal.getName()));
    }

}
