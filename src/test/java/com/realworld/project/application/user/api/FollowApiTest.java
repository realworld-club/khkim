package com.realworld.project.application.user.api;

import com.realworld.project.fixture.RestAssuredExtension;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.realworld.project.fixture.UserFixture.usernameA;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RestAssuredExtension.class)
class FollowApiTest {

    @Test
    void follow() {
    }

    @Test
    void unFollow() {
    }
}