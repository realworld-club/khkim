package com.realworld.project.application.article.api.dto;

import lombok.Getter;

@Getter
public class RequestArticleCondition {
    private String tag;
    private String author;
    private String favorited;

    public RequestArticleCondition(String tag, String author, String favorited) {
        this.tag = tag;
        this.author = author;
        this.favorited = favorited;
    }
}
