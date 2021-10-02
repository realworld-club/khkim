package com.realworld.project.application.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.application.user.domain.Profile;
import lombok.Getter;

@Getter
@JsonRootName("profile")
public class ResponseProfile {
    private String username;
    private String bio;
    private String image;
    private boolean following;

    public ResponseProfile(String username, String bio, String image, boolean following) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public static ResponseProfile of(Profile profile, boolean following) {
        return new ResponseProfile(
                profile.getUsername(),
                profile.getBio(),
                profile.getImage(),
                following);
    }
}
