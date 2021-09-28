package com.realworld.project.user.application;

import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ConfigurationPropertiesScan()
@SpringBootTest(properties = "spring.config.location=classpath:application-test.yml")
class UserFacadeTest {

    @Autowired
    @Qualifier(value = "userA")
    private UserFixtureTest userA;

    @Autowired
    @Qualifier(value = "userB")
    private UserFixtureTest userB;

    @Autowired
    private UserFacade userFacade;

    @MockBean
    private UserRepository userRepository;

    @Order(3)
    @Test
    void test3() {
        System.out.println(userA.getBio());
    }

    @Order(2)
    @Test
    void test2() {
        System.out.println(userA.getBio());
        userA.setBio("aaa");
        System.out.println(userA.getBio());
        System.out.println(userB.getBio());
    }

    @Order(1)
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