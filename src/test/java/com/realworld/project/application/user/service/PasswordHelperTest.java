package com.realworld.project.application.user.service;

import com.realworld.project.core.config.WebSecurityConfig;
import com.realworld.project.fixture.UserFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.realworld.project.fixture.UserFixture.password;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordHelperTest {

    @DisplayName("패스워드 encode")
    @Test
    void create() {
        //given
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //when
        String encodedPassword = PasswordHelper.encode(password);
        //then
        boolean matches = passwordEncoder.matches(password, encodedPassword);
        assertThat(matches).isTrue();
    }
}