package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.Users;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
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

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .profile(new Profile(username))
                .build();
    }
}
