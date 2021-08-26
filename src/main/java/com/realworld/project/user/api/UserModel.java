package com.realworld.project.user.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.project.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class UserModel {
    String email;
    String token;
    String username;
    String bio;
    String image;

    public static UserModel fromEntity(User user) {
        return new UserModel(
                user.getEmail(),
                user.getToken(),
                user.getUsername(),
                user.getProfile().getBio(),
                user.getProfile().getImage());
    }
}
