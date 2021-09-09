package com.realworld.project.user;

import com.realworld.project.fixture.Token;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ArticleControllerunit {
    private static Response result;

    public static ValidatableResponse createArticleApi(String input, Token token) {
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token.getData())
                .body(input)
                .post("/articles");

        return getResponse(response);
    }

    public static Response assertion() {
        return result;
    }
    private static ValidatableResponse getResponse(Response response) {
        result = response;
        return response.then();
    }
}
