package com.realworld.project.scenario;

import com.realworld.project.fixture.Token;
import io.restassured.response.Response;
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
        registerApi(register_json);
        Response res = loginApi(user_json, token);

        res.then()
                .statusCode(200)
                .body("user.email", equalTo(email))
                .body("user.username", equalTo(username));
    }

    @Test
    void 업데이트테스트() {
        registerApi(register_json);
        loginApi(user_json, token);
        Response res = updateApi(update_json, token);

        res.then()
                .statusCode(200)
                .body("user.email", equalTo(new_email))
                .body("user.image", equalTo(new_image))
                .body("user.bio", equalTo(new_bio));
    }

    @Test
    void 팔로우_테스트() {

    }
    
    @AfterEach
    void after() {
        if(token.getData() != null) deleteApi(username, token);
        if(new_token.getData() != null) deleteApi(username, new_token);
        token = null;
        new_token = null;

    }
}
