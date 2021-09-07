package com.realworld.project.user.api;

import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.api.wrapper.UserModelWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserLoginRequestWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserRegisterRequestWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserUpdateRequestWrapper;
import com.realworld.project.user.application.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.google.common.base.Preconditions.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/users/login")
    public UserModelWrapper<UserModel> login(@RequestBody UserLoginRequestWrapper request) {
        return new UserModelWrapper<>(userFacade.login(request.getContent()));
    }

    @PostMapping("/users")
    public UserModelWrapper<UserModel> register(@RequestBody UserRegisterRequestWrapper request) {
        return new UserModelWrapper<>(userFacade.register(request.getContent()));
    }

    @GetMapping("/user")
    public UserModelWrapper<UserModel> getCurrentUser(Principal principal){
        return new UserModelWrapper<>(userFacade.getCurrentUser(principal.getName()));
    }

    @PutMapping("/user")
    public UserModelWrapper<UserModel> updateUser(@RequestBody UserUpdateRequestWrapper request, Principal principal){
        checkNotNull(principal.getName());

        return new UserModelWrapper<>(userFacade.update(request.getContent(), principal.getName()));
    }

    @DeleteMapping("/user")
    public boolean updateUser(String username){
        checkNotNull(username);

        return userFacade.delete(username);
    }

}
