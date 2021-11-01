package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.article.api.dto.ResponseArticle;
import com.realworld.project.application.user.repository.UserRepository;
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

import static com.realworld.project.fixture.ArticleFixture.*;
import static com.realworld.project.fixture.UserFixture.*;
import static com.realworld.project.fixture.UserFixture.makeUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class FavoriteServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        makeUser(userRepository);
    }

    @DisplayName("좋아요 추가 테스트")
    @Test
    void add() {
        //given
        RequestCreateArticle requestCreateArticle = new RequestCreateArticle(title, description, body, tags());
        ResponseArticle article = articleService.create(email, requestCreateArticle);
        //when
        ResponseArticle responseArticle = favoriteService.add(slug, email);
        //then
        assertThat(responseArticle.isFavorited()).isTrue();
        assertThat(responseArticle.getFavoriteCount()).isEqualTo(1);

    }
}