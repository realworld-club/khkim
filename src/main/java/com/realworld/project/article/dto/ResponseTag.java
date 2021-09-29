package com.realworld.project.article.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseTag {
    List<String> tags;
}
