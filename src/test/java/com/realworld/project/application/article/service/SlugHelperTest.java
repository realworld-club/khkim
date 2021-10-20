package com.realworld.project.application.article.service;

import com.realworld.project.fixture.ArticleFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.realworld.project.fixture.ArticleFixture.slug;
import static com.realworld.project.fixture.ArticleFixture.title;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SlugHelperTest {

    @DisplayName("- 추가 테스트")
    @Test
    void convert_case1() {
        String convert = SlugHelper.convert(title);

        assertThat(convert).isEqualTo(slug);
    }

    @DisplayName("toLowerCase 테스트")
    @Test
    void convert_case2() {
        String convert = SlugHelper.convert("Article Test");

        assertThat(convert).isEqualTo("article-test");
    }
}