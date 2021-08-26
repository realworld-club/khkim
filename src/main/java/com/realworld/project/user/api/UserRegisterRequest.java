package com.realworld.project.user.api;

import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class UserRegisterRequest {
    String email;
    String password;
    String username;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
