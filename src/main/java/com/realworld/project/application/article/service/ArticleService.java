package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
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
}
