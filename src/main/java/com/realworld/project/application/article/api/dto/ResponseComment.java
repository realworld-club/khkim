package com.realworld.project.application.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.application.article.domain.Comment;
import com.realworld.project.application.user.api.dto.ResponseProfile;
import lombok.Builder;
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

    @Builder
    public ResponseComment(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String body, ResponseProfile author) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.body = body;
        this.author = author;
    }

    public static ResponseComment from(Comment comment) {
        return ResponseComment.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedDate())
                .updatedAt(comment.getModifiedDate())
                .body(comment.getBody())
                .author(ResponseProfile.of(comment.getAuthor().getProfile(), false))
                .build();
    }
}
