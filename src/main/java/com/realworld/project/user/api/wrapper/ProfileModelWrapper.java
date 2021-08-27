package com.realworld.project.user.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.user.api.ProfileModel;
import lombok.Value;

@Value
public class ProfileModelWrapper {
    @JsonProperty("profile")
    ProfileModel content;
}
