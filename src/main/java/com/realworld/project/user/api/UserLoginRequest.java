package com.realworld.project.user.api;

import com.realworld.project.user.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    private String email;
    private String password;

}