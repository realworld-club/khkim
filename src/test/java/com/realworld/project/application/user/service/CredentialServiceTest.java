package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.api.dto.ResponseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class CredentialServiceTest {

    @Autowired
    CredentialService credentialService;

    @BeforeEach
    void before() {
        register_user(credentialService);
    }

    @DisplayName("로그인")
    @Test
    void login() {
        //given
        RequestLoginUser requestLoginUser = new RequestLoginUser(email, password);
        //when
        ResponseUser responseUser = credentialService.login(requestLoginUser);
        //then
        assertThat(responseUser.getEmail()).isEqualTo(email);
        assertThat(responseUser.getUsername()).isEqualTo(username);
        assertThat(responseUser.getToken()).isNotNull();
    }

    @DisplayName("회원가입")
    @Test
    void registerUsers() {
        //given
        RequestRegisterUser requestRegisterUser = new RequestRegisterUser(username, email, password);
        //when
        ResponseUser responseUser = credentialService.registerUsers(requestRegisterUser);
        //then
        assertThat(responseUser.getUsername()).isEqualTo(username);
        assertThat(responseUser.getEmail()).isEqualTo(email);
    }
}