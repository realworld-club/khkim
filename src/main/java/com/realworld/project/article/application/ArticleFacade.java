package com.realworld.project.article.application;

import com.realworld.project.article.api.dto.ArticleCreateRequest;
import com.realworld.project.article.api.dto.ArticleModel;
import com.realworld.project.article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleFacade {

    private final ArticleService articleService;
    private final TagService tagService;

    @Transactional
    public ArticleModel createArticle(ArticleCreateRequest request, String writeUsername) {
        //article 등록
        Article article = articleService.createArticle(request, writeUsername);
        //tag 등록
        tagService.register(request.getTagList(), article);

        return ArticleModel.fromEntity(article);
    }

}
