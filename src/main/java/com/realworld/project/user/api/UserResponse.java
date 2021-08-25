package com.realworld.project.user.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.project.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@Getter
@Setter
@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
public class UserResponse {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public UserResponse(User user) {
        email = user.getEmail();
        username = user.getUsername();
        token = user.getToken();
        bio = user.getBio();
        image = user.getImage();
    }
}
