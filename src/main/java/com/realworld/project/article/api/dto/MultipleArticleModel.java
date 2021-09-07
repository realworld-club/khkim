package com.realworld.project.article.api.dto;

import com.realworld.project.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultipleArticleModel {
    private List<ArticleModel> articles;

    public static MultipleArticleModel fromEntity(List<Article> articles) {
        List<ArticleModel> list = articles.stream()
                .map(ArticleModel::fromEntity)
                .collect(toList());
        return new MultipleArticleModel(list);
    }
}
