package com.realworld.project.user.application;

import com.realworld.project.user.domain.Follow;
import com.realworld.project.user.domain.FollowRepository;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static com.google.common.base.Preconditions.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class ProfileService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public User getProfile(String username, String loginUser) {
        checkNotNull(username);
        checkNotNull(loginUser);

        User target = getUser(username);
        User user = getUser(loginUser);
        Optional<Follow> follow = followRepository
                .findByFollowUserIdAndFolloweeUserId(target.getId(), user.getId());

        if(follow.isPresent())
            target.followingTrue();

        return target;
    }

    @Transactional
    public User follow(String username, String loginUser) {
        checkNotNull(username);
        checkNotNull(loginUser);

        User follower = getUser(username);
        User followee = getUser(loginUser);
        Follow follow = new Follow(follower, followee);
        follower.followingTrue();

        followRepository.save(follow);

        return follower;
    }

    @Transactional
    public User unFollow(String username, String loginUser) {
        checkNotNull(username);
        checkNotNull(loginUser);

        User follower = getUser(username);
        User followee = getUser(loginUser);

        Follow follow = followRepository.findByFollowUserIdAndFolloweeUserId(follower.getId(), followee.getId())
                .orElseThrow(() -> new RuntimeException());

        followRepository.delete(follow);

        return follower;
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("runtime exception"));
    }

}
