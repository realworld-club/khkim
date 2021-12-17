package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.fixture.ApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static com.realworld.project.fixture.UserFixture.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

class CredentialApiTest extends ApiTest {

    @DisplayName("회원등록")
    @Test
    void test_case1() {
        registrationApi(username, email, password);

        response
            .then()
            .statusCode(200)
            .body("user.email", is(email))
            .body("user.username", is(username));
    }

    @DisplayName("회원등록후 로그인")
    @Test
    void test_case2() {
        registrationApi(username, email, password);
        loginApi(email, password);

        response
            .then()
            .statusCode(200)
            .body("user.email", is(email))
            .body("user.username", is(username))
            .body("user.token", Matchers.notNullValue());
    }

    public static void loginApi(String email, String password) {
        response = given()
                .contentType(ContentType.JSON)
                .body(new RequestLoginUser(email, password))
                .when()
                .post("/api/users/login");

        token = response.jsonPath().get("user.token");
    }

    public static void registrationApi(String username, String email, String password) {
        response =  given()
                .contentType(ContentType.JSON)
                .body(new RequestRegisterUser(username, email, password))
                .when()
                .post("/api/users");
    }
}