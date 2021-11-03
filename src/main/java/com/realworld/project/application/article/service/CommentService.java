package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.RequestComment;
import com.realworld.project.application.article.api.dto.ResponseComment;
import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.article.domain.Comment;
import com.realworld.project.application.article.repository.ArticleRepository;
import com.realworld.project.application.article.repository.CommentRepository;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseComment create(String slug, RequestComment requestComment, String userEmail) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Comment entity = Comment.of(requestComment, article, user);
        Comment comment = commentRepository.save(entity);

        return ResponseComment.from(comment);
    }
}
