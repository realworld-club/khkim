package com.realworld.project.article.application;

import com.realworld.project.article.domain.Article;
import com.realworld.project.article.domain.Tag;
import com.realworld.project.article.domain.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class TagService {
    private final TagRepository tagRepository;

    /**
     * tag 를 저장하는 효율적인 방법은 무엇일까?
     * - 1. unique 조건을 주고 exception 을 처리한다
     * - 2. find 로 duplicate 를 체크후 save 한다
     * @param tagList
     * @param article
     */
    public void register(Set<String> tagList, Article article) {
        for (String s : tagList) {
            Optional<Tag> tagRepositoryByName = tagRepository.findByName(s);
            if(tagRepositoryByName.isPresent())
                continue;

            Tag tag = new Tag(s, article);
            tagRepository.save(tag);
            article.getTagList().add(tag);
        }
    }
}
