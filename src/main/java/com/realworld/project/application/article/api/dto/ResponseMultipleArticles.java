package com.realworld.project.application.article.api.dto;

import com.realworld.project.application.article.domain.Article;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMultipleArticles {
    private List<ResponseArticle> articles;
    private int articlesCount;

    public static ResponseMultipleArticles of(List<Article> articles) {

        return null;
    }
}
