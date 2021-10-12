package com.realworld.project.application.article.domain;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.core.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "article")
public class Article extends BaseEntity {

    @Id @Column(name = "article_id")
    @GeneratedValue
    private Long id;

    private String slug;
    private String title;
    private String description;
    private String body;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private Set<Tag> tagList = new HashSet<>();
    private boolean favorited;
    private int favoritesCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Article(String slug, String title, String description, String body, Set<Tag> tagList, boolean favorited, int favoritesCount, User user) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.user = user;
    }

    public static Article of(RequestCreateArticle createArticle, User user) {
        return Article.builder()
                .title(createArticle.getTitle())
                .slug(createArticle.getTitle())
                .description(createArticle.getDescription())
                .body(createArticle.getBody())
                .favoritesCount(0)
                .favorited(false)
                .user(user)
//                .tagList(tagList)
                .build();
    }
}
