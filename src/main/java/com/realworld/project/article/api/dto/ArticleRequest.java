package com.realworld.project.article.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    private String tag;
    private String author;
    private String favorited;
    private int limit;
    private int offset;
}
