package com.realworld.project.article.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMultipleArticles {
    private List<ResponseArticle> articles;
    private int articlesCount;
}
