package com.realworld.project.application.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.domain.Tag;
import com.realworld.project.application.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@NoArgsConstructor
@JsonRootName("article")
public class RequestCreateArticle {
    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String body;

    private Set<Tag> tagList;

    public Article toEntity(User user) {
        return Article.builder()
                .title(title)
                .slug(title)
                .description(description)
                .body(body)
                .favoritesCount(0)
                .favorited(false)
                .user(user)
                .tagList(tagList)
                .build();
    }
}
