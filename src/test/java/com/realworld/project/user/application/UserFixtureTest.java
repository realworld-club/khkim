package com.realworld.project.user.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
public class UserFixtureTest {
	public String image;
	public String password;
	public boolean following;
	public String bio;
	public int id;
	public String email;
	public String username;
	public String token;

}
