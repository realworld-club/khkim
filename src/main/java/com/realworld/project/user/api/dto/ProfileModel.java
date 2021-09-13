package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        return new ProfileModel(
                user.getUsername(),
                user.getBio(),
                user.getImage(),
                user.isFollowing());
    }
}
