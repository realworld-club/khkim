package com.realworld.project.application.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

@Getter
@JsonRootName("article")
public class RequestUpdateArticle {
    private String title;
    private String description;
    private String body;
}
