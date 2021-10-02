package com.realworld.project.application.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.fixture.UserFixture.*;

@DisplayName("팔로우관련 테스트")
@Transactional
@SpringBootTest
class FollowServiceTest {

    @Autowired
    CredentialService credentialService;

    @Autowired
    FollowService followService;

    @BeforeEach
    void before() {
        register_user(credentialService);
        register_userA(credentialService);
    }

    @DisplayName("팔로우")
    @Test
    void follow() {
        //when
        followService.follow(username, usernameA);
        //then


    }
}