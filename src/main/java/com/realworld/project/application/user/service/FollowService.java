package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.domain.Follow;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.FollowRepository;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;

@Transactional
@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public ResponseProfile follow(String userEmail, String targetUsername) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        User targetUser = userRepository.findByProfileUsername(targetUsername)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        Follow follow = new Follow(user, targetUser);
        follow.following();
        followRepository.save(follow);

        return ResponseProfile.of(targetUser.getProfile(), true);
    }
}
