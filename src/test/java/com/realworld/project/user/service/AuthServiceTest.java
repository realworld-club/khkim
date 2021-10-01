package com.realworld.project.user.service;

import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.api.dto.RequestLoginUser;
import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.api.dto.ResponseUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @BeforeEach
    void before() {
        register(userService);
    }

    @DisplayName("로그인")
    @Test
    void login() {
        //given
        RequestLoginUser requestLoginUser = new RequestLoginUser(email, password);
        //when
        ResponseUser responseUser = authService.login(requestLoginUser);
        //then
        assertThat(responseUser.getEmail()).isEqualTo(email);
        assertThat(responseUser.getUsername()).isEqualTo(username);
        assertThat(responseUser.getToken()).isNotNull();
    }

}