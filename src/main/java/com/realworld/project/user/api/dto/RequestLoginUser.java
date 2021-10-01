package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@JsonRootName("user")
public class RequestLoginUser {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public RequestLoginUser(@NotEmpty String email, @NotEmpty String password) {
        this.email = email;
        this.password = password;
    }
}