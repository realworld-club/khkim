package com.realworld.project.nonProduct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realworld.project.article.api.TagResponse;
import com.realworld.project.user.api.ProfileResponse;
import com.realworld.project.user.api.UserResponse;
import com.realworld.project.user.domain.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ResponseTest {
    @Test
    void userResponseTest() throws IOException {
        User user = User.builder()
                .email("test@test.com")
                .bio("bio")
                .image(null)
                .username("username")
                .token("token")
                .build();
        UserResponse userResponse = new UserResponse(user);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("user.json"), userResponse);
    }

    @Test
    void profileResponseListTest() throws IOException {

        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setUsername("username");
        profileResponse.setBio("bio");
        profileResponse.setImage("image");
        profileResponse.setFollowing(false);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("user.json"), profileResponse);
    }

    @Test
    void tagResponseTest() throws IOException {
        TagResponse t = new TagResponse();
        Set<String> tags = new HashSet<>();

        tags.add("abc");
        tags.add("def");
        tags.add("ghi");

        t.setTags(tags);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("user.json"), t);
    }
}
