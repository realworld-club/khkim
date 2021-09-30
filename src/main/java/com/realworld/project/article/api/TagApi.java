package com.realworld.project.article.api;

import com.realworld.project.article.api.dto.ResponseTag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagApi {

    /**
     * Tag 가져오기
     *
     * @return tag 리스트
     */
    @GetMapping("/api/tags")
    public ResponseEntity<ResponseTag> getTags() {

        return ResponseEntity.ok(null);
    }
}
