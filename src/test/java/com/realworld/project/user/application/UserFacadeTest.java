package com.realworld.project.user.application;

import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.domain.FollowRepository;
import com.realworld.project.user.domain.UserRepository;
import com.realworld.project.user.infra.WebSecurityConfig;
import com.realworld.project.user.infra.jwt.JwtAuthenticationFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserFacadeTest {

    @Autowired
    private UserFacade userFacade;

    @MockBean
    private UserRepository userRepository;

    @Test
    void register() {
        //given
        BDDMockito.given(userRepository.save(BDDMockito.any()))
                .willReturn(UserFixture.ofEntity());
        //when
        UserModel register = userFacade.register(UserFixture.ofRegisterRequest());
        //then
        Assertions.assertThat(register).isEqualTo(UserFixture.ofModel());
    }
}