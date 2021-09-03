package com.realworld.project.user.application;

import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import static com.google.common.base.Preconditions.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class ProfileService {

    //TODO follow 시 entity 를 가져오는것은 비용이 많이든다
    private final UserRepository userRepository;

    public User getProfile(String username, String loginUser) {
        checkNotNull(username);
        checkNotNull(loginUser);

        User user = getUser(username);

        boolean match = user.getFollow().stream()
                .anyMatch(u -> u.getUsername().equals(loginUser));

        if(match)
            user.statusIsFollow();

        return user;
    }

    @Transactional
    public User follow(String username, String loginUser) {
        checkNotNull(username);
        checkNotNull(loginUser);

        User follower = getUser(loginUser);
        User user = getUser(username);

        user.following(follower);

        return user;
    }

    @Transactional
    public User unFollow(String username, String loginUser) {
        User follower = getUser(loginUser);
        User user = getUser(username);

        user.unfollow(follower);

        return user;
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("runtime exception"));
    }

}
