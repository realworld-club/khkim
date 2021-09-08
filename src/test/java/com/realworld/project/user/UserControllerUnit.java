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

        return getResponse(response);
    }


    public static ValidatableResponse loginApi(String input, Token token) {
        Response response = given()
                .contentType("application/json")
                .body(input)
                .post("/users/login");

        token.generateToken("Token " + response.jsonPath().get("user.token"));

        return getResponse(response);
    }

    public static ValidatableResponse followApi(String username, Token token) {
        Response response = given()
                .contentType("application/json")
                .pathParam("username", username)
                .header("Authorization", token.getData())
                .post("/profiles/{username}/follow");

        return getResponse(response);
    }

    public static ValidatableResponse unFollowApi(String username, Token token) {
        Response response = given()
                .contentType("application/json")
                .pathParam("username", username)
                .header("Authorization", token.getData())
                .delete("/profiles/{username}/follow");

        return getResponse(response);
    }

    public static ValidatableResponse getProfileApi(String username, Token token) {
        Response response = given()
                .pathParam("username", username)
                .header("Authorization", token.getData())
                .get("/profiles/{username}");

        return getResponse(response);
    }

    public static ValidatableResponse updateApi(String input, Token token) {
        Response response = given()
                .header("Authorization", token.getData())
                .contentType("application/json")
                .body(input)
                .put("/user");

        return getResponse(response);
    }

    public static ValidatableResponse deleteApi(String input, Token token) {
        Response response = given()
                .header("Authorization", token.getData())
                .param("username", input)
                .delete("/user");

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
