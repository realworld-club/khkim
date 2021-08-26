package com.realworld.project.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.realworld.project.article.api.ArticleModel;
import com.realworld.project.article.api.CommentModel;
import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.Comment;
import com.realworld.project.article.domain.Tag;
import com.realworld.project.user.api.ProfileModel;
import com.realworld.project.user.api.UserModel;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ModelTest {

    User user;
    Set<Tag> tagList;

    @BeforeEach
    void init() {
        user = new User(
                "username",
                "password",
                "email",
                "token",
                new Profile("bio", "\"https://static.productionready.io/images/smiley-cyrus.jpg\"", true));


        tagList = new HashSet<>();
        tagList.add(new Tag("tag1"));
        tagList.add(new Tag("tag2"));
        tagList.add(new Tag("tag3"));
    }

    @Test
    void userModelTest() throws IOException {
        UserModel userModel = UserModel.fromEntity(user);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(userModel));
    }

    @Test
    void profileModelTest() throws JsonProcessingException {
        ProfileModel profileModel = ProfileModel.fromEntity(user);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(profileModel));
    }

    @Test
    void tagModelTest() {
    }

    @Test
    void singleArticle() throws JsonProcessingException {
        Article article = Article.builder()
                .author(user)
                .slug("slug")
                .title("title")
                .description("description")
                .body("body")
                .tagList(tagList)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .favorited(false)
                .favoritesCount(0)
                .build();
        ArticleModel articleModel = ArticleModel.fromEntity(article);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(articleModel));
    }

    @Test
    void multipleArticles() {

    }

    @Test
    void singleComment() throws JsonProcessingException {
        Comment comment = Comment.builder()
                .id(1L)
                .author(user)
                .body("body")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        CommentModel commentModel = CommentModel.fromEntity(comment);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(commentModel));
    }

    @Test
    void multipleComments() {

    }
}
