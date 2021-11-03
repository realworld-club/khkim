package com.realworld.project.application.article.api;

import com.realworld.project.application.article.api.dto.RequestComment;
import com.realworld.project.application.article.api.dto.ResponseComment;
import com.realworld.project.application.article.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentApi {

    private final CommentService commentService;

    /**
     * 해당 slug의 글에 댓글 추가
     *
     * @param slug slug
     * @param requestComment 댓글내용
     * @return 댓글
     */
    @PostMapping("/api/articles/{slug}/comments")
    public ResponseEntity<ResponseComment> addComment(
            @PathVariable("slug") String slug,
            @Valid @RequestBody RequestComment requestComment,
            Principal principal) {

        ResponseComment responseComment = commentService.create(slug, requestComment, principal.getName());

        return ResponseEntity.ok(responseComment);
    }

    /**
     * 해당 slug의 모든댓글 가져오기
     *
     * @param slug slug
     * @return 댓글 리스트
     */
    @GetMapping("/api/articles/{slug}/comments")
    public ResponseEntity<List<ResponseComment>> getComments(
            @PathVariable("slug") String slug) {

        return ResponseEntity.ok(null);
    }

    /**
     * 댓글 삭제
     *
     * @param slug slug
     * @param id 댓글 id
     * @return none
     */
    @DeleteMapping("/api/articles/{slug}/comments/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("slug") String slug,
            @PathVariable("id") Long id) {

        return ResponseEntity.noContent().build();
    }
}
