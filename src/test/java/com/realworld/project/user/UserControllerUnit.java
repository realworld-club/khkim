package com.realworld.project.user;

import com.realworld.project.fixture.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserControllerUnit {
    public static void init() {
        RestAssured.port = 8081;
    }

    public static Response registerApi(String input) {
        return given()
                    .contentType("application/json")
                    .body(input)
                    .post("/users");
    }

    public static Response loginApi(String input, Token token) {
        Response response = given()
                .contentType("application/json")
                .body(input)
                .post("/users/login");

        token.generateToken("Token " + response.jsonPath().get("user.token"));
        return response;
    }


    public static Response currentUserApi() {
        return when()
                .get("/users/login");
    }

    public static Response updateApi(String input, Token token) {
        return given()
                .header("Authorization", token.getData())
                .contentType("application/json")
                .body(input)
                .put("/user");
    }

    public static Response deleteApi(String input, Token token) {
        return given()
                .header("Authorization", token.getData())
                .param("username", input)
                .delete("/user");
    }
}
