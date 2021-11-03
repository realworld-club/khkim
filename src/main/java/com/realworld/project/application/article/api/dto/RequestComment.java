package com.realworld.project.application.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@JsonRootName("comment")
public class RequestComment {
    @NotEmpty
    public String body;

    public RequestComment(String body) {
        this.body = body;
    }
}
