package com.realworld.project.user.api;

import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import lombok.Value;

@Value
public class ProfileModel {

    ProfileModelNested profile;

    public static ProfileModel fromEntity(User user) {
        return new ProfileModel(ProfileModelNested.fromEntity(user));
    }

    @Value
    public static class ProfileModelNested {
        String username;
        String bio;
        String image;
        boolean following;

        public static ProfileModelNested fromEntity(User user) {
            return new ProfileModelNested(
                    user.getUsername(),
                    user.getProfile().getBio(),
                    user.getProfile().getImage(),
                    user.getProfile().isFollowing());
        }

    }
}
