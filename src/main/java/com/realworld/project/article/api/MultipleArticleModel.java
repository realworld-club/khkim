package com.realworld.project.article.api;

import com.realworld.project.article.domain.Article;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
public class MultipleArticleModel {
    List<ArticleModel> articles;

    public static MultipleArticleModel fromEntity(List<Article> articles) {
        List<ArticleModel> list = articles.stream()
                .map(ArticleModel::fromEntity)
                .collect(toList());
        return new MultipleArticleModel(list);
    }
}
