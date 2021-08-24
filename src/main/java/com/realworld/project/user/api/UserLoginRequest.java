package com.realworld.project.user.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    String email;
    String password;
}
