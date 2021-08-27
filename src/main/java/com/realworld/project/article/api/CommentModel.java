package com.realworld.project.article.api;

import com.realworld.project.article.domain.aggregate.Comment;
import com.realworld.project.user.api.ProfileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

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
