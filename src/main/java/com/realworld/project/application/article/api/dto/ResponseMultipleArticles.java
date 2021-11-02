package com.realworld.project.application.article.api.dto;

import com.realworld.project.application.article.domain.Article;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseMultipleArticles {
    private List<ResponseArticle> articles;
    private int articlesCount;

    public ResponseMultipleArticles(List<ResponseArticle> articles, int articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }

    public static ResponseMultipleArticles from(List<Article> articles) {
        return new ResponseMultipleArticles(
                articles.stream()
                        .map(article -> ResponseArticle.from(article))
                        .collect(Collectors.toList()),
                articles.size());
    }
}
