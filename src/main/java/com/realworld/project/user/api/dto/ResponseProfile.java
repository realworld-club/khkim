package com.realworld.project.user.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

@Getter
@JsonRootName("profile")
public class ResponseProfile {
    private String username;
    private String bio;
    private String image;
    private String following;

}
