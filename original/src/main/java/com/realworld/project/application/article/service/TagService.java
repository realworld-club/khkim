package com.realworld.project.application.article.service;

import com.realworld.project.application.article.api.dto.ResponseTag;
import com.realworld.project.application.article.domain.Tag;
import com.realworld.project.application.article.repository.TagRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public ResponseTag getList() {
        List<Tag> tags = tagRepository.findAll();

        return ResponseTag.from(tags);
    }
}
