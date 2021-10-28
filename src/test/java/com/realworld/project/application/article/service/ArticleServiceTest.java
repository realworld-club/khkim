package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.article.api.dto.ResponseArticle;
import com.realworld.project.application.article.domain.Tag;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import com.realworld.project.fixture.ArticleFixture;
import com.realworld.project.fixture.UserFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.realworld.project.fixture.ArticleFixture.*;
import static com.realworld.project.fixture.UserFixture.*;
import static com.realworld.project.fixture.UserFixture.email;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        makeUser(userRepository);
    }

    @DisplayName("게시판 생성 테스트")
    @Test
    void create() {
        //given
        RequestCreateArticle requestCreateArticle = new RequestCreateArticle(title, description, body, tags());
        //when
        ResponseArticle article = articleService.create(email, requestCreateArticle);
        //then
        assertThat(article.getSlug()).isEqualTo(slug);
        assertThat(article.getBody()).isEqualTo(body);
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getDescription()).isEqualTo(description);
        assertThat(article.isFavorited()).isEqualTo(false);
        assertThat(article.getFavoriteCount()).isEqualTo(0);
        assertThat(article.getCreatedAt()).isNotNull();
        assertThat(article.getUpdatedAt()).isNotNull();
        assertThat(article.getTagList()).isEqualTo(tags());

    }

    @DisplayName("게시판 조회 테스트")
    @Test
    void get() {
        //given
        RequestCreateArticle requestCreateArticle = new RequestCreateArticle(title, description, body, tags());
        articleService.create(email, requestCreateArticle);
        //when
        ResponseArticle article = articleService.getBySlug(slug);
        //then
        assertThat(article.getSlug()).isEqualTo(slug);
        assertThat(article.getBody()).isEqualTo(body);
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getDescription()).isEqualTo(description);
        assertThat(article.isFavorited()).isEqualTo(false);
        assertThat(article.getFavoriteCount()).isEqualTo(0);
        assertThat(article.getCreatedAt()).isNotNull();
        assertThat(article.getUpdatedAt()).isNotNull();
        assertThat(article.getTagList()).isEqualTo(tags());
    }

    @DisplayName("게시판 삭제 테스트")
    @Test
    void delete() {
        //given
        RequestCreateArticle requestCreateArticle = new RequestCreateArticle(title, description, body, tags());
        articleService.create(email, requestCreateArticle);
        //when
        articleService.delete(slug);
        //then
        assertThrows(BusinessException.class, () -> articleService.getBySlug(slug)
        );
    }
}