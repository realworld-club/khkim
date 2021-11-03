package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.RequestComment;
import com.realworld.project.application.article.api.dto.ResponseArticle;
import com.realworld.project.application.article.api.dto.ResponseComment;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.fixture.ArticleFixture;
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
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        makeUser(userRepository);
    }

    @DisplayName("댓글 추가")
    @Test
    void crate() {
        //given
        ResponseArticle article = articleService.create(email, makeRequestCreateArticle());
        //when
        ResponseComment responseComment = commentService.create(slug, new RequestComment(comment), email);
        //then
        assertThat(responseComment.getBody()).isEqualTo(comment);
        assertThat(responseComment.getAuthor().getUsername()).isEqualTo(username);
        assertThat(responseComment.getId()).isNotNull();


    }

}
