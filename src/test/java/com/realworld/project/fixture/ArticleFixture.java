package com.realworld.project.fixture;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.domain.Tag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArticleFixture {
    public static final String slug = "article-test";
    public static final String title = "article test";
    public static final String description = "article test description";
    public static final String body = "article test body";
    public static final String tag1 = "tag1";
    public static final String tag2 = "tag2";
    public static final String tag3 = "tag3";

    public static final String slugA = "article-a-test";
    public static final String titleA = "article a test";
    public static final String descriptionA = "article a test description";
    public static final String bodyA = "article a test body";

    public static final String comment = "comment test";
    public static final String commentA = "comment testA";
    public static final String commentB = "comment testB";
    public static final String commentC = "comment testC";

    public static Set<String> tags() {
        Set<String> tags = new HashSet<>();
        tags.addAll(Arrays.asList(tag1, tag2, tag3));
        return tags;
    }

    public static RequestCreateArticle makeRequestCreateArticle() {
        return new RequestCreateArticle(title, description, body, tags());
    }
}
