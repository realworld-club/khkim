package com.realworld.project.application.article.domain;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.article.api.dto.RequestUpdateArticle;
import com.realworld.project.application.article.service.SlugHelper;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.core.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_tag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_favorite_user",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> favoriteUsers = new HashSet<>();
    @Transient
    private boolean favorited = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;


    @Builder
    public Article(String slug, String title, String description, String body, Set<User> favoriteUsers, User author, Set<Tag> tags) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.author = author;
        this.tags = tags;
    }

    public static Article of(RequestCreateArticle createArticle, User author) {
        Set<Tag> tags = createArticle.getTagList().stream()
                .map(Tag::new)
                .collect(Collectors.toSet());

        return Article.builder()
                .title(createArticle.getTitle())
                .slug(SlugHelper.convert(createArticle.getTitle()))
                .description(createArticle.getDescription())
                .body(createArticle.getBody())
                .tags(tags)
                .author(author)
                .build();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getArticles().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getArticles().remove(this);
    }

    public void update(RequestUpdateArticle article) {
        if(StringUtils.hasText(article.getTitle())) {
            this.title = article.getTitle();
            this.slug = SlugHelper.convert(this.title);
        }

        if(StringUtils.hasText(article.getBody()))
            this.body = article.getBody();


        if(StringUtils.hasText(article.getDescription()))
            this.description = article.getDescription();

    }

    public void addFavoriteUser(User user) {
        favoriteUsers.add(user);
        user.getArticles().add(this);
    }

    public void removeFavorieUser(User user) {
        favoriteUsers.remove(user);
        user.getArticles().remove(this);
    }

    public void setFavorite(User user) {
        favorited = favoriteUsers.contains(user);
    }

    public int getFavoritesCount() {
        if(favoriteUsers == null)
            return 0;

        return favoriteUsers.size();
    }
}
