package com.realworld.project.user;

import com.realworld.project.fixture.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class UserControllerUnit {
    private static Response result;

    public static void init() {
        RestAssured.port = 8081;
    }

    public static ValidatableResponse registerApi(String input) {
        Response response = given()
                .contentType("application/json")
                .body(input)
                .post("/users");

        result = response;
        return response.then();
    }

    public static ValidatableResponse loginApi(String input, Token token) {
        Response response = given()
                .contentType("application/json")
                .body(input)
                .post("/users/login");

        token.generateToken("Token " + response.jsonPath().get("user.token"));

        result = response;
        return response.then();
    }


    public static ValidatableResponse currentUserApi() {
        Response response = when()
                .get("/users/login");

        result = response;
        return response.then();
    }

    public static ValidatableResponse updateApi(String input, Token token) {
        Response response = given()
                .header("Authorization", token.getData())
                .contentType("application/json")
                .body(input)
                .put("/user");

        result = response;
        return response.then();
    }

    public static ValidatableResponse deleteApi(String input, Token token) {
        Response response = given()
                .header("Authorization", token.getData())
                .param("username", input)
                .delete("/user");

        result = response;
        return response.then();
    }

    public static Response assertion() {
        return result;
    }
}
