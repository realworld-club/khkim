package com.realworld.project.application.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Profile {
    private String username;
    private String bio;
    private String image;

    public Profile(String username) {
        this.username = username;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateBio(String bio) {
        this.bio = bio;
    }

    public void changeImage(String image) {
        this.image = image;
    }
}
