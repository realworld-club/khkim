package com.realworld.project.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@JsonRootName("article")
public class RequestCreateArticle {
    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String body;

    private Set<String> tagList;
}
