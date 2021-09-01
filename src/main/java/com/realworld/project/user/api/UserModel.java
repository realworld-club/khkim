package com.realworld.project.user.api;

import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public static UserModel fromEntity(User user) {
        return new UserModel(
                user.getEmail(),
                user.getToken(),
                user.getUsername(),
                user.getProfile().getBio(),
                user.getProfile().getImage());
    }
}
