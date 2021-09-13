package com.realworld.project.scenario;

import com.realworld.project.fixture.Token;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.realworld.project.fixture.ArticleFixtureJson.article_json;
import static com.realworld.project.fixture.UserFixture.*;
import static com.realworld.project.fixture.UserFixtureJson.register_json;
import static com.realworld.project.fixture.UserFixtureJson.user_json;
import static com.realworld.project.scenario.ArticleControllerunit.createArticleApi;
import static com.realworld.project.scenario.UserControllerUnit.*;
import static com.realworld.project.scenario.UserControllerUnit.loginApi;
import static org.hamcrest.Matchers.*;

public class ArticleScenarioTest {
    Token token = null;
    Token new_token = null;

    @BeforeEach
    void before() {
        //TODO clear db
        init();
        token = new Token();
        new_token = new Token();
    }

    @Test
    void 게시판생성_테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);
        createArticleApi(article_json, token).statusCode(200);

        ArticleControllerunit.assertion().then()
                .body("article.slug", equalTo("how-to-train-your-dragon"))
                .body("article.title", equalTo("How to train your dragon"))
                .body("article.description", equalTo("Ever wonder how?"))
                .body("article.body", equalTo("Very carefully."))
                .body("article.tagList", hasItems("dragons", "training"))
                .body("article.favorited", equalTo(false))
                .body("article.favoritesCount", equalTo(0))
                .body("article.author.username", equalTo(username));

    }

    @AfterEach
    void after() {
        if(token.getData() != null) deleteApi(username, token).statusCode(200);
        if(new_token.getData() != null) deleteApi(new_username, new_token).statusCode(200);
        token = null;
        new_token = null;

    }
}
