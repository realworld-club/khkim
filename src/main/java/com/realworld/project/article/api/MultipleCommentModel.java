package com.realworld.project.article.api;

import com.realworld.project.article.domain.aggregate.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultipleCommentModel {
    private List<CommentModel> comments;

    public static MultipleCommentModel fromEntity(List<Comment> comments) {
        List<CommentModel> list = comments.stream()
                .map(CommentModel::fromEntity)
                .collect(toList());
        return new MultipleCommentModel(list);
    }
}
