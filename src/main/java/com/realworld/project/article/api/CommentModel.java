package com.realworld.project.article.api;

import com.realworld.project.article.domain.aggregate.Comment;
import com.realworld.project.user.api.ProfileModel;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CommentModel {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String body;
    ProfileModel author;

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
