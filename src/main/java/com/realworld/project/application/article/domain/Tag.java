package com.realworld.project.application.article.domain;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag")
public class Tag {

    @Id @Column(name = "tag_id")
    @GeneratedValue
    private Long id;
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
