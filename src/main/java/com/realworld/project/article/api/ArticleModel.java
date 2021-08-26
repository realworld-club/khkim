package com.realworld.project.article.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.Tag;
import com.realworld.project.user.api.ProfileModel;
import com.realworld.project.user.api.ProfileModel.ProfileModelNested;
import com.realworld.project.user.domain.Profile;
import com.realworld.project.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Value
public class ArticleModel {

    ArticleModelNested article;

    public static ArticleModel fromEntity(Article article) {
        return new ArticleModel(ArticleModelNested.fromEntity(article));
    }

    @Value
    public static class ArticleModelNested {
        String slug;
        String title;
        String description;
        String body;
        Set<String> tagList;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        boolean favorited;
        int favoritesCount;
        ProfileModelNested author;

        public static ArticleModelNested fromEntity(Article article) {
            //converter tag data
            Set<Tag> tagList = article.getTagList();
            Set<String> tagModelList = new LinkedHashSet<>();
            for (Tag tag : tagList) {
                tagModelList.add(tag.getName());
            }

            //converter profile data
            ProfileModelNested profileModelNested = ProfileModelNested.fromEntity(article.getAuthor());

            return new ArticleModelNested(
                    article.getSlug(),
                    article.getTitle(),
                    article.getDescription(),
                    article.getBody(),
                    tagModelList,
                    article.getCreatedAt(),
                    article.getUpdatedAt(),
                    article.isFavorited(),
                    article.getFavoritesCount(),
                    profileModelNested);
        }
    }
}