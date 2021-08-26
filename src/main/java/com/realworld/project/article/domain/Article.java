package com.realworld.project.article.domain;

import com.realworld.project.user.api.ProfileModel;
import com.realworld.project.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "article")
public class Article {

    @Id @Column(name = "article_id")
    @GeneratedValue
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false))
    Set<Tag> tagList = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean favorited;
    private int favoritesCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Builder
    public Article(String slug, String title, String description, String body, Set<Tag> tagList, LocalDateTime createdAt, LocalDateTime updatedAt, boolean favorited, int favoritesCount, User author) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.author = author;
    }
}
