package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonRootName("user")
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
                user.getBio(),
                user.getImage());
    }
}
