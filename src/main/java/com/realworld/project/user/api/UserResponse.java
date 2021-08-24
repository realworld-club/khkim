package com.realworld.project.user.api;

import com.realworld.project.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    String email;
    String token;
    String username;
    String bio;
    String image;

    public UserResponse(User user) {
        email = user.getEmail();
        username = user.getUsername();
    }
}
