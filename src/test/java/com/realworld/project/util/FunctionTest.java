package com.realworld.project.util;

import com.realworld.project.article.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionTest {

    @Test
    void slugTest() {
        //given
        String str = "How to train your dragon";
        //when
        String result = Article.generateSlug(str);
        //then
        assertThat(result).isEqualTo("how-to-train-your-dragon");
    }
}
