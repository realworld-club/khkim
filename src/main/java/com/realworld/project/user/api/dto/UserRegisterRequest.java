package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
@JsonRootName("user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String password;
    private String username;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
