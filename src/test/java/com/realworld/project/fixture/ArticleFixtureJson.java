package com.realworld.project.fixture;

import com.realworld.project.article.domain.Article;

public class ArticleFixtureJson {
    public static final String article_json = "{\"article\":{\"title\":\"How to train your dragon\", \"description\":\"Ever wonder how?\", \"body\":\"Very carefully.\", \"tagList\":[\"dragons\",\"training\"]}}";

    public static Article ofArticle() {
        return Article.builder()
                .slug("how-to-train-your-dragon")
                .title("How to train your dragon")
                .description("You have to believe")
                .body("It takes a Jacobian")
                .favorited(false)
                .favoritesCount(0)
                .author(null)
                .build();
    }
}
