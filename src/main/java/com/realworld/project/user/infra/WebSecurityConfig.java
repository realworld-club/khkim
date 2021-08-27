package com.realworld.project.user.infra;

import com.realworld.project.util.exception.AuthenticationEntryPointException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/")
                .permitAll()
            .anyRequest()
                .authenticated()
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointException());
    }

}
