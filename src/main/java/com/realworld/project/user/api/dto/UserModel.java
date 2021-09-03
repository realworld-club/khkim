package com.realworld.project.user.api.dto;

import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        Profile profile = user.getProfile();
        return new UserModel(
                user.getEmail(),
                user.getToken(),
                user.getUsername(),
                (profile != null) ? profile.getBio() : null,
                (profile != null) ? profile.getImage() : null);
    }
}
