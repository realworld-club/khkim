package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.ResponseArticle;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.repository.ArticleRepository;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FavoriteService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseArticle add(String slug, String userEmail) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        article.addFavoriteUser(user);
        article.setFavorite(user);

        return ResponseArticle.from(article);
    }

    @Transactional
    public ResponseArticle remove(String slug, String userEmail) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        article.removeFavorieUser(user);
        article.setFavorite(user);

        return ResponseArticle.from(article);
    }
}
