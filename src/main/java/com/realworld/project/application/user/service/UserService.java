package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.*;
import com.realworld.project.application.user.domain.Follow;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.FollowRepository;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;
import static com.realworld.project.core.exception.ErrorCode.USER_PROFILE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseUser getUserInfo(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        return ResponseUser.from(user);
    }

    @Transactional
    public ResponseProfile getProfile(String userEmail, String targetUsername) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(USER_PROFILE_NOT_FOUND));
        User targetUser = userRepository.findByProfileUsername(targetUsername)
                .orElseThrow(() -> new BusinessException(USER_PROFILE_NOT_FOUND));

        List<Follow> follows = user.getFollowing();
        boolean matchResult = follows.stream()
                .anyMatch(f -> f.followingUserName().equals(targetUsername));

        return ResponseProfile.of(targetUser.getProfile(), matchResult);
    }

    @Transactional
    public ResponseUser update(String userEmail, RequestUpdateUser requestUpdateUser) {
        checkUnique(requestUpdateUser);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        user.update(requestUpdateUser);

        return ResponseUser.from(user);
    }

    private void checkUnique(RequestUpdateUser requestUpdateUser) {
        boolean emailNotUnique = userRepository.findByEmail(requestUpdateUser.getEmail()).isPresent();
        boolean usernameNotUnique = userRepository.findByProfileUsername(requestUpdateUser.getUsername()).isPresent();

        if(emailNotUnique)
            throw new BusinessException(ErrorCode.EMAIL_NOT_UNIQUE);

        if(usernameNotUnique)
            throw new BusinessException(ErrorCode.USERNAME_NOT_UNIQUE);
    }
}
