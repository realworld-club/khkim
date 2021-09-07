package com.realworld.project.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class UserControllerUnit {
    public static String token = null;
    public static void init() {
        RestAssured.port = 8081;
    }

    private static void generateToken(Response response) {
        token = "Token ";
        token += response.jsonPath().get("user.token");
    }

    public static Response registerApi(String input) {
        return given()
                    .contentType("application/json")
                    .body(input)
                    .post("/users");
    }

    public static Response loginApi(String input) {
        Response response = given()
                .contentType("application/json")
                .body(input)
                .post("/users/login");

        generateToken(response);
        return response;
    }


    public static Response currentUserApi() {
        return when()
                .get("/users/login");
    }

    public static Response updateApi(String input) {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(input)
                .put("/user");
    }

    public static Response deleteApi(String username) {
        return given()
                .header("Authorization", token)
                .param("username", username)
                .delete("/user");
    }
}
