package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@JsonRootName("profile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
    private String username;
    private String bio;
    private String image;
    private boolean following;

    public static ProfileModel fromEntity(User user) {
        Profile profile = user.getProfile();
        return new ProfileModel(
                user.getUsername(),
                (profile != null) ? profile.getBio() : null,
                (profile != null) ? profile.getImage() : null,
                (profile != null) ? profile.isFollowing() : false);
    }
}
