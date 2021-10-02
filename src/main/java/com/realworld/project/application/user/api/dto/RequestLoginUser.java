package com.realworld.project.application.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
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
