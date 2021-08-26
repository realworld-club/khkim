package com.realworld.project.article.api;

import com.realworld.project.article.api.ArticleModel.ArticleModelNested;
import com.realworld.project.article.domain.Article;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleArticleModel {
    List<ArticleModelNested> articles;

    public static MultipleArticleModel fromEntity(List<Article> articles) {
        List<ArticleModelNested> list = articles.stream()
                .map(ArticleModelNested::fromEntity)
                .collect(toList());
        return new MultipleArticleModel(list);
    }
}
