package com.realworld.project.application.article.repository;

import com.realworld.project.application.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleQueryDslRepository {
    Optional<Article> findBySlug(String slug);

    @Query("select a " +
            "from User u " +
            "inner join u.following f on f.follower.email = :userEmail " +
            "inner join Article a on a.author = f.following " +
            "order by a.id desc ")
    List<Article> findFeedArticles(@Param("userEmail") String userEmail, Pageable pageable);
}
