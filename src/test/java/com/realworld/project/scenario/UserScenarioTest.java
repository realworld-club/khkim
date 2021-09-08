package com.realworld.project.scenario;

import com.realworld.project.fixture.Token;
import com.realworld.project.user.UserControllerUnit;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.realworld.project.fixture.UserFixture.*;
import static com.realworld.project.fixture.UserFixtureJson.*;
import static com.realworld.project.user.UserControllerUnit.*;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class UserScenarioTest {

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
    void 유저등록후_로그인_테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);

        assertion().then()
                .body("user.email", equalTo(email))
                .body("user.username", equalTo(username));
    }

    @Test
    void 업데이트테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);
        updateApi(update_json, token).statusCode(200);

        assertion().then()
                .body("user.email", equalTo(new_email))
                .body("user.image", equalTo(new_image))
                .body("user.bio", equalTo(new_bio));
    }

    @Test
    void 프로필_테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);
        getProfileApi(username, token).statusCode(200);

        assertion().then()
                .body("profile.username", equalTo(username))
                .body("profile.following", equalTo(false));
    }

    @Test
    void 팔로우_테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);
        registerApi(new_register_json).statusCode(200);
        loginApi(new_user_json, new_token).statusCode(200);

        //username 이 new_username 을 follow 하다
        followApi(new_username, token).statusCode(200);

        assertion().then()
                .body("profile.username", equalTo(new_username))
                .body("profile.following", equalTo(true));
    }

    @Test
    void 팔로우_2번_선택시_테스트() {
    }

    @Test
    void 언팔로우_테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);
        registerApi(new_register_json).statusCode(200);
        loginApi(new_user_json, new_token).statusCode(200);

        //username 이 new_username 을 follow 하다
        followApi(new_username, token).statusCode(200);
        unFollowApi(new_username, token).statusCode(200);

        assertion().then()
                .body("profile.username", equalTo(new_username))
                .body("profile.following", equalTo(false));
    }

    @Test
    void 언팔로우_2번_테스트() {
        registerApi(register_json).statusCode(200);
        loginApi(user_json, token).statusCode(200);
        registerApi(new_register_json).statusCode(200);
        loginApi(new_user_json, new_token).statusCode(200);

        //username 이 new_username 을 follow 하다
        followApi(new_username, token).statusCode(200);
        unFollowApi(new_username, token).statusCode(200);

        assertion().then()
                .body("profile.username", equalTo(new_username))
                .body("profile.following", equalTo(false));
    }

    @AfterEach
    void after() {
        if(token.getData() != null) deleteApi(username, token).statusCode(200);
        if(new_token.getData() != null) deleteApi(new_username, new_token).statusCode(200);
        token = null;
        new_token = null;

    }
}
