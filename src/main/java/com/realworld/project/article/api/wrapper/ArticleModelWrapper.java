package com.realworld.project.article.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.article.api.ArticleModel;
import lombok.Value;

@Value
public class ArticleModelWrapper {
    @JsonProperty("article")
    ArticleModel content;
}
