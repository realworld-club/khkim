package com.realworld.project.article.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.project.article.domain.Comment;
import com.realworld.project.user.api.ProfileModel;
import com.realworld.project.user.domain.Profile;
import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Value
public class CommentModel {

    CommentNested comment;

    public static CommentModel fromEntity(Comment comment) {
        return new CommentModel(CommentNested.generator(comment));
    }

    @Value
    private static class CommentNested {
        Long id;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        String body;
        ProfileModel author;

        private static CommentNested generator(Comment comment) {
            ProfileModel profileModel = ProfileModel.fromEntity(comment.getAuthor());

            return new CommentNested(
                    comment.getId(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt(),
                    comment.getBody(),
                    profileModel);
        }
    }
}
