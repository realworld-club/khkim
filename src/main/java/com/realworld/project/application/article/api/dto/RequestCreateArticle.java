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

    private Set<String> tagList;

    public RequestCreateArticle(String title, String description, String body, Set<String> tagList) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
    }
}
