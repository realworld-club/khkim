package com.realworld.project.application.article.repository;

import com.realworld.project.application.article.api.dto.RequestArticleCondition;
import com.realworld.project.application.article.domain.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleQueryDslRepository {

    List<Article> getArticlesByCondition(RequestArticleCondition condition, Pageable pageable);
}
