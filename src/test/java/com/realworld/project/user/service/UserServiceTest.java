package com.realworld.project.user.service;

import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.api.dto.ResponseUser;
import com.realworld.project.user.domain.Users;
import com.realworld.project.user.domain.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UsersRepository usersRepository;

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

    @DisplayName("유저정보 가져오기 테스트")
    @Test
    void getUserInfo() {
        userService.registerUsers( new RequestRegisterUser(username, email, password));
        Users user = getUser(usersRepository, email);
        //when
        ResponseUser responseUser = userService.getUserInfo(Long.toString(user.getId()));
        //then
        assertThat(responseUser.getUsername()).isEqualTo(username);
        assertThat(responseUser.getEmail()).isEqualTo(email);

    }
}