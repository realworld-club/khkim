package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.domain.Follow;
import com.realworld.project.application.user.domain.Users;
import com.realworld.project.application.user.repository.FollowRepository;
import com.realworld.project.application.user.repository.UsersRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;

@Transactional
@RequiredArgsConstructor
@Service
public class FollowService {

    private final UsersRepository usersRepository;
    private final FollowRepository followRepository;

    @Transactional
    public ResponseProfile follow(String userId, String targetUsername) {
        Users user = usersRepository.getById(Long.parseLong(userId));
        Users targetUser = usersRepository.findByProfileUsername(targetUsername)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        Follow follow = new Follow(user, targetUser);
        followRepository.save(follow);

        return ResponseProfile.of(targetUser.getProfile(), true);
    }
}
