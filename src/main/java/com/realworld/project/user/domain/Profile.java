package com.realworld.project.user.domain;

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
    private boolean following;

    public Profile(String username) {
        this.username = username;
        following = false;
    }
}
