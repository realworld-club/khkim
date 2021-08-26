package com.realworld.project.user.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.user.api.UserModel;
import lombok.Value;

@Value
public class UserModelWrapper {
    @JsonProperty("user")
    UserModel content;
}
