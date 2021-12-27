package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.*;
import com.realworld.project.application.article.domain.Tag;
import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.application.user.service.FollowService;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceUnitUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.realworld.project.fixture.ArticleFixture.*;
import static com.realworld.project.fixture.ArticleFixture.makeRequestCreateArticle;
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

    @Autowired
    private FollowService followService;

    @BeforeEach
    void beforeEach() {
        makeUser(userRepository);
        makeUserA(userRepository);
    }

    @DisplayName("게시판 생성 테스트")
    @Test
    void create() {
        //when
        ResponseArticle article = articleService.create(email, makeRequestCreateArticle());
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
        articleService.create(email, makeRequestCreateArticle());
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
        articleService.create(email, makeRequestCreateArticle());
        //when
        articleService.delete(slug);
        //then
        assertThrows(BusinessException.class, () -> articleService.getBySlug(slug)
        );
    }

    @DisplayName("게시판 수정 테스트")
    @Test
    void update() {
        //given
        articleService.create(email, makeRequestCreateArticle());
        RequestUpdateArticle requestUpdateArticle = new RequestUpdateArticle(titleA, descriptionA, bodyA);
        //when
        ResponseArticle update = articleService.update(slug, requestUpdateArticle);
        //then
        assertThat(update.getSlug()).isEqualTo(slugA);
        assertThat(update.getBody()).isEqualTo(bodyA);
        assertThat(update.getTitle()).isEqualTo(titleA);
        assertThat(update.getDescription()).isEqualTo(descriptionA);
    }

    @DisplayName("follow 유저의 게시글 가져오기")
    @Test
    void getFeeds() {
        //given
        articleService.create(emailA, makeRequestCreateArticle());
        followService.follow(email, usernameA);
        //when
        Pageable pageable = PageRequest.of(0, 10);
        ResponseMultipleArticles feeds = articleService.getFeeds(email, pageable);
        //then
        assertThat(feeds.getArticlesCount()).isEqualTo(1);
        assertThat(feeds.getArticles().get(0).getTitle()).isEqualTo(title);
    }

    @DisplayName("게시글 가져오기")
    @Test
    void getArticles() {
        //given
        articleService.create(email, makeRequestCreateArticle());
        articleService.create(emailA, makeRequestCreateArticle());
        //when
        ResponseMultipleArticles articles = articleService.getArticles(null, null);
        //then
        assertThat(articles.getArticlesCount()).isEqualTo(2);
        assertThat(articles.getArticles().get(0).getAuthor().getUsername()).isEqualTo(username);
        assertThat(articles.getArticles().get(1).getAuthor().getUsername()).isEqualTo(usernameA);
    }

    @DisplayName("게시글 조건별 가져오기")
    @Test
    void getArticlesCase1() {
        //given
        articleService.create(email, makeRequestCreateArticle());
        articleService.create(emailA, makeRequestCreateArticle());
        RequestArticleCondition requestArticleCondition = new RequestArticleCondition(tag2, username, null);
        Pageable pageable = PageRequest.of(0, 10);
        //when
        ResponseMultipleArticles articles = articleService.getArticles(requestArticleCondition, pageable);
        //then
        assertThat(articles.getArticlesCount()).isEqualTo(1);
        assertThat(articles.getArticles().get(0).getAuthor().getUsername()).isEqualTo(username);
    }
}