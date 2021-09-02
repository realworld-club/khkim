package com.realworld.project.user.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@ToString
@EqualsAndHashCode
public class Profile {
    private String bio;
    private String image;
    private boolean following;

    public Profile(String bio, String image, boolean following) {
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public void changeBio(String bio) {
        this.bio = bio;
    }

    public void changeImage(String image) {
        this.image = image;
    }
}
