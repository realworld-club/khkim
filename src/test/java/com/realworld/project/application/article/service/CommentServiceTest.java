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

import java.util.List;

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
        articleService.create(email, makeRequestCreateArticle());
        //when
        ResponseComment responseComment = commentService.create(slug, new RequestComment(comment), email);
        //then
        assertThat(responseComment.getBody()).isEqualTo(comment);
        assertThat(responseComment.getAuthor().getUsername()).isEqualTo(username);
        assertThat(responseComment.getId()).isNotNull();
    }
    
    @DisplayName("해당 slug 의 모든 댓글 가져오기")
    @Test
    void getComment() {
        //given
        articleService.create(email, makeRequestCreateArticle());
        commentService.create(slug, new RequestComment(comment), email);
        commentService.create(slug, new RequestComment(commentA), email);
        commentService.create(slug, new RequestComment(commentB), email);
        commentService.create(slug, new RequestComment(commentC), email);
        //when
        List<ResponseComment> comments = commentService.getComment(slug);
        //then
        assertThat(comments.size()).isEqualTo(4);
        assertThat(comments)
                .extracting("body")
                .contains(comment, commentA, commentB, commentC);
    }

    @DisplayName("댓글 삭제")
    @Test
    void delete() {
        //given
        articleService.create(email, makeRequestCreateArticle());
        ResponseComment responseComment = commentService.create(slug, new RequestComment(comment), email);
        //when
        commentService.delete(slug, responseComment.getId());
        //then
        List<ResponseComment> comments = commentService.getComment(slug);
        assertThat(comments.size()).isEqualTo(0);
    }

}
