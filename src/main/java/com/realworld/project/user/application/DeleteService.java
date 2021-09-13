package com.realworld.project.user.application;

import com.realworld.project.article.domain.ArticleRepository;
import com.realworld.project.user.domain.Follow;
import com.realworld.project.user.domain.FollowRepository;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DeleteService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ArticleRepository articleRepository;

    public void delete(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);

        List<Follow> followList = followRepository.findByFollowUserIdOrFolloweeUserId(user.getId(), user.getId());
        followList.stream().forEach(followRepository::delete);

        articleRepository.deleteByAuthorId(user.getId());

        userRepository.deleteByUsername(username);
    }
}
