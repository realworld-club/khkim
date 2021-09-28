package com.realworld.project.user.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

@Configuration
public class TestBean {

    @Bean
    void testBeanMethod() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println("sleep i = " + i);
            Thread.sleep(3000);
        }
    }
}
