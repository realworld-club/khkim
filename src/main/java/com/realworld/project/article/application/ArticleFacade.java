package com.realworld.project.article.application;

import com.realworld.project.article.api.dto.ArticleCreateRequest;
import com.realworld.project.article.api.dto.ArticleModel;
import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.ArticleRelationTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleFacade {

    private final ArticleService articleService;
    private final TagService tagService;

    @Transactional
    public ArticleModel createArticle(ArticleCreateRequest request, String writeUsername) {
        tagService.create(request.getTagList());

        Article article = articleService.createArticle(request, writeUsername);

        //tag list 처리
        return ArticleModel.fromEntity(article);
    }

}
