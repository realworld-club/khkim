package com.realworld.project.core.jpa;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
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
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @DisplayName("Audit 적용 테스트")
    @Test
    void test() {
        RequestCreateArticle requestCreateArticle = new RequestCreateArticle(title, description, body, tags());
        Article article = articleRepository.save(Article.of(requestCreateArticle, makeUser(userRepository)));

        assertThat(article.getCreatedDate()).isNotNull();
        assertThat(article.getModifiedDate()).isNotNull();
    }
}