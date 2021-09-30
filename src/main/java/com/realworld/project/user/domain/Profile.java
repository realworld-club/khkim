package com.realworld.project.user.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Profile {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
