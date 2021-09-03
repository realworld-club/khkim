package com.realworld.project.user.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.user.api.dto.ProfileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModelWrapper<T> {
    @JsonProperty("profile")
    private T content;

}
