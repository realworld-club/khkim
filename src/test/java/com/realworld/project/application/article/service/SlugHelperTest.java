package com.realworld.project.application.article.service;

import com.realworld.project.fixture.ArticleFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.realworld.project.fixture.ArticleFixture.slug;
import static com.realworld.project.fixture.ArticleFixture.title;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SlugHelperTest {

    @Test
    void convert() {
        String convert = SlugHelper.convert(title);

        assertThat(convert).isEqualTo(slug);

    }
}