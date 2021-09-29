package com.realworld.project.article.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@JsonRootName("article")
public class RequestUpdateArticle {
    private String title;
    private String description;
    private String body;
}
