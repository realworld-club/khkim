package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.fixture.RestAssuredExtension;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.realworld.project.fixture.UserFixture.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@ExtendWith(RestAssuredExtension.class)
class CredentialApiTest {

    @DisplayName("회원등록후 로그인")
    @Test
    void test_case1() {
        registration(username, email, password);
        login(email, password);
    }

    public static void login(String email, String password) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(new RequestLoginUser(email, password))
                .when()
                .post("/api/users/login");

        response
                .then()
                .statusCode(200)
                .body("user.email", is(email))
                .body("user.username", is(username))
                .body("user.token", Matchers.notNullValue());

        token = response.jsonPath().get("user.token");
    }

    public static void registration(String username, String email, String password) {
        given()
                .contentType(ContentType.JSON)
                .body(new RequestRegisterUser(username, email, password))
        .when()
                .post("/api/users")
        .then()
                .statusCode(200)
                .body("user.email", is(email))
                .body("user.username", is(username));
    }
}