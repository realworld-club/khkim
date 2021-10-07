package com.realworld.project.core.jpa;

import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.repository.ArticleRepository;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.fixture.ArticleFixture;
import com.realworld.project.fixture.UserFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.realworld.project.fixture.ArticleFixture.*;
import static com.realworld.project.fixture.UserFixture.makeUser;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class BaseEntityTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @DisplayName("Audit 적용 테스트")
    @Test
    void test() {
        Article article = articleRepository.save(new Article(slug, title, description, body, null, false, 0, makeUser(userRepository)));

        assertThat(article.getCreatedDate()).isNotNull();
        assertThat(article.getModifiedDate()).isNotNull();
    }
}