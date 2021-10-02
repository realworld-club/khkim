package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.RequestRegisterUser;
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
    UsersRepository usersRepository;

    @BeforeEach
    void before() {
        register(credentialService);
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

    @DisplayName("유저 Profile 가져오기")
    @Test
    void getProfile() {
        //when
        ResponseProfile profile = userService.getProfile(username);
        //then
        assertThat(profile.getUsername()).isEqualTo(username);
        assertThat(profile.isFollowing()).isEqualTo(false);

    }

    @DisplayName("유저정보 갱신")
    @Test
    void update() {
        //given
        Users user = getUser(usersRepository, email);
        RequestUpdateUser requestUpdateUser =
                new RequestUpdateUser(emailA, bioA, imageA, usernameA, passwordA);
        //when
        ResponseUser update = userService.update(Long.toString(user.getId()), requestUpdateUser);
        //then
        assertThat(update.getEmail()).isEqualTo(emailA);
        assertThat(update.getBio()).isEqualTo(bioA);
        assertThat(update.getImage()).isEqualTo(imageA);
        assertThat(update.getUsername()).isEqualTo(usernameA);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users userA = getUser(usersRepository, emailA);
        boolean matches = passwordEncoder.matches(passwordA, userA.getPassword());
        assertThat(matches).isTrue();
    }
}