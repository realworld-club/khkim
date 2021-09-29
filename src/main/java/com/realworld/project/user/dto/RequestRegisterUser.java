package com.realworld.project.user.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
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
}