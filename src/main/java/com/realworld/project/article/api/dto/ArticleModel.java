package com.realworld.project.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.Tag;
import com.realworld.project.user.api.dto.ProfileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@JsonRootName("article")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModel {
    private String slug;
    private String title;
    private String description;
    private String body;
    private Set<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean favorited;
    private int favoritesCount;
    private ProfileModel author;

    public static ArticleModel fromEntity(Article article) {
        //converter tag data
        Set<String> tagModelList = new LinkedHashSet<>();

        //converter profile data
        ProfileModel profileModel = ProfileModel.fromEntity(article.getAuthor());

        return new ArticleModel(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                tagModelList,
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.isFavorited(),
                article.getFavoritesCount(),
                profileModel);
    }
}
