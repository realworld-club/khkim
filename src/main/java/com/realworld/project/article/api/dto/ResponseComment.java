package com.realworld.project.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.user.api.dto.ResponseProfile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonRootName("comment")
public class ResponseComment {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String body;
    private ResponseProfile author;

}