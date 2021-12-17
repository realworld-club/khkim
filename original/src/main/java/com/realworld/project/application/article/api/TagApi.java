package com.realworld.project.application.article.api;

import com.realworld.project.application.article.api.dto.ResponseTag;
import com.realworld.project.application.article.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagApi {

    private final TagService tagService;
    /**
     * Tag 가져오기
     *
     * @return tag 리스트
     */
    @GetMapping("/api/tags")
    public ResponseEntity<ResponseTag> getTags() {

        ResponseTag responseTag = tagService.getList();
        return ResponseEntity.ok(responseTag);
    }
}
