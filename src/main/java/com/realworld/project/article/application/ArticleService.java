package com.realworld.project.article.application;

import com.realworld.project.article.api.dto.ArticleCreateRequest;
import com.realworld.project.article.api.dto.ArticleModel;
import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.ArticleRepository;
import com.realworld.project.article.domain.Tag;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ArticleModel createArticle(ArticleCreateRequest request, String writeUsername) {
        Set<Tag> tags = new HashSet<>();
        request.getTagList().stream()
                .map(t -> tags.add(new Tag(t)));

        User user = userRepository.findByUsername(writeUsername)
                .orElseThrow(() -> new RuntimeException());

        Article article = Article.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .body(request.getBody())
                .tagList(tags)
                .author(user)
                .build();

        Article savedArticle = articleRepository.save(article);

        return ArticleModel.fromEntity(savedArticle);
    }
}
