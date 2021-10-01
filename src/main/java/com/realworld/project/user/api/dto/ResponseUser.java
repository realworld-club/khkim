package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonRootName("user")
public class ResponseUser {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public ResponseUser(String email, String token, Profile profile) {
        this.email = email;
        this.token = token;
        this.username = profile.getUsername();
        this.bio = profile.getBio();
        this.image = profile.getImage();
    }

    public static ResponseUser of(Users user, String token) {
        return new ResponseUser(user.getEmail(), token, user.getProfile());
    }

    public static ResponseUser of(Users user) {
        return new ResponseUser(user.getEmail(), null, user.getProfile());
    }
}
