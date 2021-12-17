package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.ResponseArticle;
import com.realworld.project.application.article.api.dto.ResponseTag;
import com.realworld.project.application.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.fixture.ArticleFixture.*;
import static com.realworld.project.fixture.UserFixture.email;
import static com.realworld.project.fixture.UserFixture.makeUser;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class TagServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        makeUser(userRepository);
    }

    @DisplayName("Tag 리스트 테스트")
    @Test
    void getList() {
        //given
        articleService.create(email, makeRequestCreateArticle());
        //when
        ResponseTag list = tagService.getList();
        //then
        assertThat(list.getTags()).contains(tag1, tag2, tag3);
    }
}
