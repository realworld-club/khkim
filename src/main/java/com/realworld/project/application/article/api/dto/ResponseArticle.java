package com.realworld.project.application.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
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
}