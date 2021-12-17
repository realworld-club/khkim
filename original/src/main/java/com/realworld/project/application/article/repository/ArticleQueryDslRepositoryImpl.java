package com.realworld.project.application.article.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.project.application.article.api.dto.RequestArticleCondition;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.domain.QArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.realworld.project.application.article.domain.QArticle.article;

@RequiredArgsConstructor
public class ArticleQueryDslRepositoryImpl implements ArticleQueryDslRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Article> getArticlesByCondition(RequestArticleCondition condition, Pageable pageable) {
        return query
                .selectFrom(article)
                .where(
                        condition(condition.getTag(), article.tags.any().tagName::eq),
                        condition(condition.getAuthor(), article.author.profile.username::eq),
                        condition(condition.getFavorited(), article.favoriteUsers.any().profile.username::eq)
                )
                .orderBy(article.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private <T> BooleanExpression condition(T value, Function<T, BooleanExpression> function) {
        return Optional.ofNullable(value).map(function).orElse(null);
    }
}
