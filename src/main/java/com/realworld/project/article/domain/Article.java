package com.realworld.project.article.domain;

import com.realworld.project.article.domain.Tag;
import com.realworld.project.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean favorited;
    private int favoritesCount;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private Set<Tag> tagList = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Builder
    public Article(String slug, String title, String description, String body, LocalDateTime createdAt, LocalDateTime updatedAt, boolean favorited, int favoritesCount, User author) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.author = author;
    }

    /*
        v 띄어쓰기는 하이픈(-)으로 대체
        v 대문자는 소문자로
        쉼표나 마침표 등 기호를 자동으로 삭제
     */
    public static String generateSlug(String context) {
        context = context.replaceAll(" ", "-");
        context = context.toLowerCase();
        return context;
    }
}
