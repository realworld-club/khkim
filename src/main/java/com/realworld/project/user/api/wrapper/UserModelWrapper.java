package com.realworld.project.user.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.user.api.UserLoginRequest;
import com.realworld.project.user.api.UserModel;
import com.realworld.project.user.api.UserRegisterRequest;
import com.realworld.project.user.api.UserUpdateRequest;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModelWrapper<T> {
    @JsonProperty("user")
    private T content;

    @NoArgsConstructor
    public static class UserLoginRequestWrapper extends UserModelWrapper<UserLoginRequest>{
        public UserLoginRequestWrapper(UserLoginRequest userLoginRequest) {
            super(userLoginRequest);
        }
    }

    @NoArgsConstructor
    public static class UserRegisterRequestWrapper extends UserModelWrapper<UserRegisterRequest>{
        public UserRegisterRequestWrapper(UserRegisterRequest userRegisterRequest) {
            super(userRegisterRequest);
        }
    }

    @NoArgsConstructor
    public static class UserUpdateRequestWrapper extends UserModelWrapper<UserUpdateRequest>{
        public UserUpdateRequestWrapper(UserUpdateRequest userUpdateRequest) {
            super(userUpdateRequest);
        }
    }

}
