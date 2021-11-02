package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.article.api.dto.RequestUpdateArticle;
import com.realworld.project.application.article.api.dto.ResponseArticle;
import com.realworld.project.application.article.api.dto.ResponseMultipleArticles;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.domain.Tag;
import com.realworld.project.application.article.repository.ArticleRepository;
import com.realworld.project.application.article.repository.TagRepository;
import com.realworld.project.application.user.domain.Follow;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseArticle create(String userEmail, RequestCreateArticle requestCreateArticle) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Article entity = Article.of(requestCreateArticle, user);
        Article article = articleRepository.save(entity);

        return ResponseArticle.from(article);
    }

    public ResponseArticle getBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

        return ResponseArticle.from(article);
    }

    @Transactional
    public void delete(String slug) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

        articleRepository.delete(article);
    }

    @Transactional
    public ResponseArticle update(String slug, RequestUpdateArticle requestUpdateArticle) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

        article.update(requestUpdateArticle);

        return ResponseArticle.from(article);
    }

    public ResponseMultipleArticles getFeeds(String userEmail, Pageable pageable) {
        userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<Article> articles = articleRepository.findFeedArticles(userEmail, pageable);

        return ResponseMultipleArticles.from(articles);
    }
}
