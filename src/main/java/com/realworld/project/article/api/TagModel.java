package com.realworld.project.article.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Set;

@Value
public class TagModel {
    Set<String> tags;
}
