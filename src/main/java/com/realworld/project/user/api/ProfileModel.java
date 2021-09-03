package com.realworld.project.user.api;

import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
    private String username;
    private String bio;
    private String image;
    private boolean following;

    public static ProfileModel fromEntity(User user) {
        return new ProfileModel(
                user.getUsername(),
                user.getProfile().getBio(),
                user.getProfile().getImage(),
                user.getProfile().isFollowing());
    }
}
