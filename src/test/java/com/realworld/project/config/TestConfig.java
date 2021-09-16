package com.realworld.project.config;

import com.realworld.project.user.application.UserFixtureTest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    @ConfigurationProperties("user")
    public UserFixtureTest userA() {
        return new UserFixtureTest();
    }

    @Bean
    @ConfigurationProperties("new.user")
    public UserFixtureTest  userB() {
        return new UserFixtureTest();
    }

}
