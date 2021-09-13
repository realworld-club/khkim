package com.realworld.project.article.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.project.article.domain.aggregate.Comment;
import com.realworld.project.user.api.dto.ProfileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonRootName("comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String body;
    private ProfileModel author;

    public static CommentModel fromEntity(Comment comment) {
        ProfileModel profileModel = ProfileModel.fromEntity(comment.getAuthor());

        return new CommentModel(
                comment.getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getBody(),
                profileModel);
    }
}
