package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.RequestUpdateUser;
import com.realworld.project.fixture.ApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.realworld.project.application.user.api.CredentialApiTest.loginApi;
import static com.realworld.project.application.user.api.CredentialApiTest.registrationApi;
import static com.realworld.project.fixture.UserFixture.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

class UserApiTest extends ApiTest {

    @DisplayName("유저정보 가져오기")
    @Test
    void test_case1() {
        registrationApi(username, email, password);
        loginApi(email, password);
        getUserApi();

        response
            .then()
            .statusCode(200)
            .body("user.email", is(email))
            .body("user.username", is(username));
    }

    @DisplayName("사용자 정보 업데이트")
    @Test
    void test_case2() {
        registrationApi(username, email, password);
        loginApi(email, password);
        updateUserApi(new RequestUpdateUser(emailA, bioA, imageA, usernameA, passwordA));

        response
            .then()
            .statusCode(200)
            .body("user.email", is(emailA))
            .body("user.bio", is(bioA))
            .body("user.image", is(imageA))
            .body("user.username", is(usernameA));
    }

    @DisplayName("타겟 프로필정보")
    @Test
    void test_case3() {
        registrationApi(username, email, password);
        registrationApi(usernameA, emailA, passwordA);
        loginApi(email, password);
        getProfileApi(usernameA);

        response
            .then()
            .statusCode(200)
            .body("user.email", is(emailA))
            .body("user.bio", is(bioA))
            .body("user.username", is(usernameA))
            .body("user.following", is(false));
    }

    private static void getProfileApi(String username) {
        response = given()
                .header("Authorization", "Bearer " + token)
        .when()
                .get("/api/profiles/{username}", username);

    }

    public static void updateUserApi(RequestUpdateUser requestUpdateUser) {
        response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestUpdateUser)
                .when()
                .put("/api/user");
    }

    public static void getUserApi() {
        response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/user");
    }
}