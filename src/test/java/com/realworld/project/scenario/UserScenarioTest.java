package com.realworld.project.scenario;

import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.UserControllerUnit;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.realworld.project.fixture.UserFixture.*;
import static com.realworld.project.user.UserControllerUnit.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class UserScenarioTest {
    String register = String.format(register_json);
    String login = String.format(user_json);
    String update = String.format(update_json);

    @BeforeEach
    void before() {
        init();
    }

    @Test
    void 유저등록후_로그인_테스트() {
        registerApi(register);
        Response res = loginApi(login);

        res.then()
                .statusCode(200)
                .body("user.email", equalTo(email))
                .body("user.username", equalTo(username));
    }

    @Test
    void 업데이트테스트() {
        registerApi(register);
        loginApi(login);
        Response res = updateApi(update);

        res.then()
                .statusCode(200)
                .body("user.email", equalTo(new_email))
                .body("user.image", equalTo(new_image))
                .body("user.bio", equalTo(new_bio));
    }


    @AfterEach
    void after() {
        Response response = deleteApi(username);
        Boolean result = response.then()
                .statusCode(200)
                .extract().response().as(Boolean.class);

        assertThat(result).isTrue();

    }
}
