package com.realworld.project.article.api;

import com.realworld.project.article.api.CommentModel.CommentModelNested;
import com.realworld.project.article.domain.Comment;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.*;

@Value
public class MultipleCommentModel {

    List<CommentModelNested> comments;

    public static MultipleCommentModel fromEntity(List<Comment> comments) {
        List<CommentModelNested> list = comments.stream()
                .map(CommentModelNested::fromEntity)
                .collect(toList());
        return new MultipleCommentModel(list);
    }
}
