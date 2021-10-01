package com.realworld.project.application.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@JsonRootName("user")
public class RequestUpdateUser {
    @NotEmpty
    private String email;

    @NotEmpty
    private String bio;

    @NotEmpty
    private String image;

    private String username;
    private String password;

    public RequestUpdateUser(@NotEmpty String email, @NotEmpty String bio, @NotEmpty String image, String username, String password) {
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.username = username;
        this.password = password;
    }
}
