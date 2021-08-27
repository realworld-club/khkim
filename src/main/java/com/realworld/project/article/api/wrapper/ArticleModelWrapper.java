package com.realworld.project.article.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.article.api.ArticleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModelWrapper {
    @JsonProperty("article")
    private ArticleModel content;
}
