package com.realworld.project.application.article.api;

import com.realworld.project.application.article.api.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class ArticleApi {

    /**
     * 글리스트 요청
     * - 글리스트는 기본 최근순으로 나열한다
     *
     *
     * @param condition filter 조건
     * @param pageable 페이징 조건
     * @return 글 리스트
     */
    @GetMapping("/api/articles")
    public ResponseEntity<ResponseMultipleArticles> getArticles(
            RequestArticleCondition condition, Pageable pageable) {

        ResponseMultipleArticles responseMultipleArticles = null;

        return ResponseEntity.ok(responseMultipleArticles);
    }

    /**
     * follow 유저의 글리스트를 가져온다
     *
     * @param pageable 페이징 정보
     * @return 글 리스트
     */
    @GetMapping("/api/articles/feed")
    public ResponseEntity<ResponseMultipleArticles> feedArticles(Pageable pageable) {

        ResponseMultipleArticles responseMultipleArticles = null;

        return ResponseEntity.ok(responseMultipleArticles);
    }

    /**
     * 글 가져오기
     *
     * @param slug slug 요청
     * @return 글
     */
    @GetMapping("/api/articles/{slug}")
    ResponseEntity<ResponseArticle> getArticle(@PathVariable("slug") String slug) {

        ResponseArticle responseArticle = null;

        return ResponseEntity.ok(responseArticle);
    }

    /**
     * 글 생성
     *
     * @param requestCreateArticle
     * @return
     */
    @PostMapping("/api/articles")
    ResponseEntity<ResponseArticle> createArticle(
            @Valid @RequestBody RequestCreateArticle requestCreateArticle) {

        ResponseArticle responseArticle = null;

        return ResponseEntity.ok(responseArticle);
    }

    /**
     * 글 수정
     * - title이 변경되면 slug도 업데이트 되어야 한다
     *
     * @param slug slug 값
     * @param requestUpdateArticle 변경되어야할 field
     * @return 글
     */
    @PutMapping("/api/articles/{slug}")
    public ResponseEntity<ResponseArticle> updateArticle(
            @PathVariable("slug") String slug,
            @Valid @RequestBody RequestUpdateArticle requestUpdateArticle) {

        ResponseArticle responseArticle = null;

        return ResponseEntity.ok(responseArticle);
    }

    /**
     * 글 삭제
     *
     * @param slug
     * @return
     */
    @DeleteMapping("/api/articles/{slug}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("slug") String slug) {
        return ResponseEntity.noContent().build();
    }

    /**
     * 글 좋아요 추가
     *
     * @param slug 해당 slug
     * @return 글
     */
    @PostMapping("/api/articles/{slug}/favorite")
    public ResponseEntity<ResponseArticle> favoriteArticle(
            @PathVariable("slug") String slug) {

        return ResponseEntity.ok(null);
    }

    /**
     * 글 좋아요 삭제
     *
     * @param slug 해당 slug
     * @return 글
     */
    @DeleteMapping("/api/articles/{slug}/favorite")
    public ResponseEntity<ResponseArticle> deleteFavoriteArticle(
            @PathVariable("slug") String slug) {

        return ResponseEntity.ok(null);
    }

}
