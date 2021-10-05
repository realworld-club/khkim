package com.realworld.project.application.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.application.user.domain.Profile;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.service.PasswordHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@JsonRootName("user")
public class RequestRegisterUser {
    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public RequestRegisterUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(PasswordHelper.encode(password))
                .profile(new Profile(username))
                .build();
    }
}
