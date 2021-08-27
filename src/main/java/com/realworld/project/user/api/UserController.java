package com.realworld.project.user.api;

import com.realworld.project.user.api.wrapper.UserModelWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserLoginRequestWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserRegisterRequestWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper.UserUpdateRequestWrapper;
import com.realworld.project.user.application.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/login")
    public UserModelWrapper login(@RequestBody UserLoginRequestWrapper request) {
        return new UserModelWrapper(userFacade.login(request.getContent()));
    }

    @PostMapping
    public UserModelWrapper register(@RequestBody UserRegisterRequestWrapper request) {
        return new UserModelWrapper(userFacade.register(request.getContent()));
    }

    @GetMapping
    public UserModelWrapper getAuthentication(){
//        return new UserModelWrapper(userFacade.getAuth());
        return null;
    }

    @PutMapping
    public UserModelWrapper updateUser(@RequestBody UserUpdateRequestWrapper request){
        return new UserModelWrapper(null);
    }

}
