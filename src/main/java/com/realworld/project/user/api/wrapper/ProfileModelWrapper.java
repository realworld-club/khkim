package com.realworld.project.user.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.user.api.ProfileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModelWrapper {
    @JsonProperty("profile")
    private ProfileModel content;
}
