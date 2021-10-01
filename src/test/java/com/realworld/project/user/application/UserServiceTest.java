package com.realworld.project.user.application;

import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.api.dto.ResponseUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @DisplayName("회원가입 테스트")
    @Test
    void registerUsers() {
        //given
        RequestRegisterUser requestRegisterUser = new RequestRegisterUser(username, email, password);
        //when
        ResponseUser responseUser = userService.registerUsers(requestRegisterUser);
          //then
        assertThat(responseUser.getUsername()).isEqualTo(username);
        assertThat(responseUser.getEmail()).isEqualTo(email);
    }
}