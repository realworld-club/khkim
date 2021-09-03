package com.realworld.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.realworld.project.article.api.ArticleModel;
import com.realworld.project.article.api.CommentModel;
import com.realworld.project.article.api.MultipleArticleModel;
import com.realworld.project.article.api.MultipleCommentModel;
import com.realworld.project.article.api.wrapper.ArticleModelWrapper;
import com.realworld.project.article.api.wrapper.CommentModelWrapper;
import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.Tag;
import com.realworld.project.article.domain.aggregate.Comment;
import com.realworld.project.user.api.dto.ProfileModel;
import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.api.wrapper.ProfileModelWrapper;
import com.realworld.project.user.api.wrapper.UserModelWrapper;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        UserModelWrapper userModelWrapper = new UserModelWrapper(userModel);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(userModelWrapper));
    }

    @Test
    void profileModelTest() throws JsonProcessingException {
        ProfileModel profileModel = ProfileModel.fromEntity(user);
        ProfileModelWrapper profileWrapper = new ProfileModelWrapper(profileModel);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(profileWrapper));
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
        ArticleModelWrapper articleModelWrapper = new ArticleModelWrapper(articleModel);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(articleModelWrapper));
    }

    @Test
    void multipleArticles() throws JsonProcessingException {
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

        List<Article> list = new ArrayList<>();
        list.add(article);
        list.add(article);
        MultipleArticleModel multipleArticleModel = MultipleArticleModel.fromEntity(list);


        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(multipleArticleModel));
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
        CommentModelWrapper commentModelWrapper = new CommentModelWrapper(commentModel);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(commentModelWrapper));
    }

    @Test
    void multipleComments() throws JsonProcessingException {
        Comment comment = Comment.builder()
                .id(1L)
                .author(user)
                .body("body")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment);
        MultipleCommentModel multipleCommentModel = MultipleCommentModel.fromEntity(list);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(multipleCommentModel));
    }
}
