package com.realworld.project.user.api;

import com.realworld.project.user.domain.aggregate.User;
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
