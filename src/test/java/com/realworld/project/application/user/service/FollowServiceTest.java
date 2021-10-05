package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.domain.Follow;
import com.realworld.project.application.user.repository.FollowRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class FollowServiceTest {

    @Autowired
    CredentialService credentialService;

    @Autowired
    FollowService followService;

    @Autowired
    FollowRepository followRepository;

    @BeforeEach
    void before() {
        register_user(credentialService);
        register_userA(credentialService);
    }

    @DisplayName("팔로우")
    @Test
    void follow() {
        //when
        ResponseProfile follow = followService.follow(email, usernameA);
        List<Follow> followList = followRepository.findAll();
        //then
        assertThat(follow.isFollowing()).isTrue();
        assertThat(follow.getUsername()).isEqualTo(usernameA);
        boolean match = followList.stream().allMatch(f -> f.getFollowing().getEmail().equals(emailA));
        assertThat(match).isTrue();
    }
}