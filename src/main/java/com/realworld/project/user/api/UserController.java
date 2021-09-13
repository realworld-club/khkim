package com.realworld.project.user.api;

import com.realworld.project.user.api.dto.UserLoginRequest;
import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.api.dto.UserRegisterRequest;
import com.realworld.project.user.api.dto.UserUpdateRequest;
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
    public UserModel login(@RequestBody UserLoginRequest request) {
        return userFacade.login(request);
    }

    @PostMapping("/users")
    public UserModel register(@RequestBody UserRegisterRequest request) {
        return userFacade.register(request);
    }

    @GetMapping("/user")
    public UserModel getCurrentUser(Principal principal){
        return userFacade.getCurrentUser(principal.getName());
    }

    @PutMapping("/user")
    public UserModel updateUser(@RequestBody UserUpdateRequest request, Principal principal){
        checkNotNull(principal.getName());

        return userFacade.update(request, principal.getName());
    }

    @DeleteMapping("/user")
    public void updateUser(String username){
        checkNotNull(username);

        userFacade.delete(username);
    }

}
