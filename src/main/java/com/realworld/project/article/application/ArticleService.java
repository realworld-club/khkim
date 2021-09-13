package com.realworld.project.article.application;

import com.realworld.project.article.api.dto.ArticleCreateRequest;
import com.realworld.project.article.api.dto.ArticleModel;
import com.realworld.project.article.domain.*;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Article createArticle(ArticleCreateRequest request, String writeUsername) {
        User user = userRepository.findByUsername(writeUsername)
                .orElseThrow(() -> new RuntimeException());

        Article article = Article.builder()
                .slug(Article.generateSlug(request.getTitle()))
                .title(request.getTitle())
                .description(request.getDescription())
                .body(request.getBody())
                .author(user)
                .build();

        return articleRepository.save(article);
    }
}
