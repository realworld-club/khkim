package com.realworld.project.article.api;

import com.realworld.project.article.api.dto.ArticleCreateRequest;
import com.realworld.project.article.api.dto.ArticleModel;
import com.realworld.project.article.api.dto.MultipleArticleModel;
import com.realworld.project.article.application.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public MultipleArticleModel getArticles() {
//        return MultipleArticleModel.fromEntity()
        return null;
    }

    @PostMapping("/articles")
    public ArticleModel createArticle(
            @Valid @RequestBody ArticleCreateRequest request,
            Principal principal) {
        return articleService.createArticle(request, principal.getName());
    }

}
