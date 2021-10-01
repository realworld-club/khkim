package com.realworld.project.application.article.api.dto;

import lombok.Getter;

@Getter
public class RequestArticleCondition {
    private String tag;
    private String author;
    private String favorited;
}
