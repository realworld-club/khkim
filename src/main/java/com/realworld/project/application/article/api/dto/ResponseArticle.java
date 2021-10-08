package com.realworld.project.application.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.domain.Tag;
import com.realworld.project.application.user.api.dto.ResponseProfile;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@JsonRootName("article")
public class ResponseArticle {
    private String slug;
    private String title;
    private String description;
    private String body;
    private Set<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean favorited;
    private int favoriteCount;
    private ResponseProfile author;

    public ResponseArticle(String slug, String title, String description, String body, Set<String> tagList, LocalDateTime createdAt, LocalDateTime updatedAt, boolean favorited, int favoriteCount, ResponseProfile author) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorited = favorited;
        this.favoriteCount = favoriteCount;
        this.author = author;
    }

    public static ResponseArticle from(Article article) {
        return new ResponseArticle(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
            null,
//                article.getTagList(),
                article.getCreatedDate(),
                article.getModifiedDate(),
                article.isFavorited(),
                article.getFavoritesCount(),
                ResponseProfile.of(article.getUser().getProfile(), false));
    }

}
