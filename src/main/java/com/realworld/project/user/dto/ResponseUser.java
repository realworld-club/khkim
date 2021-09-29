package com.realworld.project.user.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

@Getter
@JsonRootName("user")
public class ResponseUser {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;
}
