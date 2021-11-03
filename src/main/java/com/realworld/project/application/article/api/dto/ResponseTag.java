package com.realworld.project.application.article.api.dto;

import com.realworld.project.application.article.domain.Tag;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseTag {
    private final List<String> tags;

    public ResponseTag(List<String> tags) {
        this.tags = tags;
    }

    public static ResponseTag from(List<Tag> tags) {
        return new ResponseTag(tags.stream()
                .map(Tag::getTagName)
                .collect(Collectors.toList()));
    }
}
