package com.realworld.project.article.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.article.api.CommentModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentModelWrapper {
    @JsonProperty("comment")
    private CommentModel content;
}
