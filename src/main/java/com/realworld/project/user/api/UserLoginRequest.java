package com.realworld.project.user.api;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class UserLoginRequest {
    String email;
    String password;
}
