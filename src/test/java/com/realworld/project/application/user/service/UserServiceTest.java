package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.RequestUpdateUser;
import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.domain.Users;
import com.realworld.project.application.user.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원관련 서비스")
@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    CredentialService credentialService;

    @Autowired
    FollowService followService;

    @Autowired
    UsersRepository usersRepository;

    @BeforeEach
    void before() {
        register_user(credentialService);
        register_userA(credentialService);
    }

    @DisplayName("유저정보 가져오기")
    @Test
    void getUserInfo() {
        //given
        Users user = getUser(usersRepository, email);
        //when
        ResponseUser responseUser = userService.getUserInfo(Long.toString(user.getId()));
        //then
        assertThat(responseUser.getUsername()).isEqualTo(username);
        assertThat(responseUser.getEmail()).isEqualTo(email);

    }

    @DisplayName("자기자신의 Profile 가져오기")
    @Test
    void getProfile_case1() {
        Users user = getUser(usersRepository, email);
        //when
        ResponseProfile profile = userService.getProfile(Long.toString(user.getId()), username);
        //then
        assertThat(profile.getUsername()).isEqualTo(username);
        assertThat(profile.isFollowing()).isEqualTo(false);
    }

    @DisplayName("following 하지 않는 상대방의 Profile 가져오기")
    @Test
    void getProfile_case2() {
        Users user = getUser(usersRepository, email);
        //when
        ResponseProfile profile = userService.getProfile(Long.toString(user.getId()), usernameA);
        //then
        assertThat(profile.getUsername()).isEqualTo(usernameA);
        assertThat(profile.isFollowing()).isEqualTo(false);
    }

    @DisplayName("following 한 상대방의 Profile 가져오기")
    @Test
    void getProfile_case3() {
        Users user = getUser(usersRepository, email);
        followService.follow(Long.toString(user.getId()), usernameA);
        //when
        ResponseProfile profile = userService.getProfile(Long.toString(user.getId()), usernameA);
        //then
        assertThat(profile.getUsername()).isEqualTo(usernameA);
        assertThat(profile.isFollowing()).isEqualTo(true);
    }


    @DisplayName("유저정보 갱신")
    @Test
    void update() {
        //given
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users user = getUser(usersRepository, email);
        RequestUpdateUser requestUpdateUser =
                new RequestUpdateUser(emailB, bioB, imageB, usernameB, passwordB);
        //when
        ResponseUser update = userService.update(Long.toString(user.getId()), requestUpdateUser);
        //then
        assertThat(update.getEmail()).isEqualTo(emailB);
        assertThat(update.getBio()).isEqualTo(bioB);
        assertThat(update.getImage()).isEqualTo(imageB);
        assertThat(update.getUsername()).isEqualTo(usernameB);
        boolean matches = passwordEncoder.matches(passwordB, getUser(usersRepository, emailB).getPassword());
        assertThat(matches).isTrue();
    }
    
}