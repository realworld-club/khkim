package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.domain.Users;
import com.realworld.project.application.user.repository.UsersRepository;
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

    @DisplayName("회원가입")
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

    @DisplayName("유저정보 가져오기")
    @Test
    void getUserInfo() {
        //given
        userService.registerUsers(new RequestRegisterUser(username, email, password));
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
        //given
        userService.registerUsers(new RequestRegisterUser(username, email, password));
        //when
        ResponseProfile profile = userService.getProfile(username);
        //then
        assertThat(profile.getUsername()).isEqualTo(username);
        assertThat(profile.isFollowing()).isEqualTo(false);

    }
}