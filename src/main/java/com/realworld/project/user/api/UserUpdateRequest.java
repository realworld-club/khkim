package com.realworld.project.user.api;

import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String email;
    private String password;
    private String username;
    private String bio;
    private String image;

}
