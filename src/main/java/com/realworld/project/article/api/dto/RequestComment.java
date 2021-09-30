package com.realworld.project.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@JsonRootName("comment")
public class RequestComment {
    @NotEmpty
    public String body;
}
