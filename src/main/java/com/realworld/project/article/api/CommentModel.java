package com.realworld.project.article.api;

import com.realworld.project.article.domain.Comment;
import com.realworld.project.user.api.ProfileModel;
import com.realworld.project.user.api.ProfileModel.ProfileModelNested;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CommentModel {

    CommentModelNested comment;

    public static CommentModel fromEntity(Comment comment) {
        return new CommentModel(CommentModelNested.fromEntity(comment));
    }

    @Value
    public static class CommentModelNested {
        Long id;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        String body;
        ProfileModelNested author;

        public static CommentModelNested fromEntity(Comment comment) {
            ProfileModelNested profileModel = ProfileModelNested.fromEntity(comment.getAuthor());

            return new CommentModelNested(
                    comment.getId(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt(),
                    comment.getBody(),
                    profileModel);
        }
    }
}
