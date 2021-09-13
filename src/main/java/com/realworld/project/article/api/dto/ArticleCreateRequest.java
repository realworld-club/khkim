package com.realworld.project.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonRootName("article")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateRequest {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String body;

    private Set<String> tagList;
}
