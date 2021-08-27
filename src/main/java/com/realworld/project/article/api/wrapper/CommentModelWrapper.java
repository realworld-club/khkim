package com.realworld.project.article.api.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.project.article.api.CommentModel;
import lombok.Value;

@Value
public class CommentModelWrapper {
    @JsonProperty("comment")
    CommentModel content;
}
