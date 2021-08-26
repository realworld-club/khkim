package com.realworld.project.article.api;

import com.realworld.project.article.domain.Comment;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.*;

@Value
public class MultipleCommentModel {

    List<CommentModel> comments;

    public static MultipleCommentModel fromEntity(List<Comment> comments) {
        List<CommentModel> list = comments.stream()
                .map(CommentModel::fromEntity)
                .collect(toList());
        return new MultipleCommentModel(list);
    }
}
