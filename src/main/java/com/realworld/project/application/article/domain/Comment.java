package com.realworld.project.application.article.domain;

import com.realworld.project.application.article.api.dto.RequestComment;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.core.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue
    private Long id;
    private String body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Comment(String body, Article article, User author) {
        this.body = body;
        this.article = article;
        this.author = author;
    }

    public static Comment of(RequestComment requestComment, Article article, User author) {
        return new Comment(requestComment.getBody(), article, author);
    }
}
