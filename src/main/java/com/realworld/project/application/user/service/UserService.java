package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.*;
import com.realworld.project.application.user.domain.Follow;
import com.realworld.project.application.user.domain.Users;
import com.realworld.project.application.user.repository.UsersRepository;
import com.realworld.project.core.exception.BusinessException;
import com.realworld.project.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;
import static com.realworld.project.core.exception.ErrorCode.USER_PROFILE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;

    public ResponseUser getUserInfo(String userId) {
        Users user = usersRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        return ResponseUser.from(user);
    }

    public ResponseProfile getProfile(String userId, String targetUsername) {
        Users user = usersRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessException(USER_PROFILE_NOT_FOUND));
        Users targetUser = usersRepository.findByProfileUsername(targetUsername)
                .orElseThrow(() -> new BusinessException(USER_PROFILE_NOT_FOUND));

        List<Follow> follows = user.getFollows();
        boolean matchResult = follows.stream()
                .anyMatch(f -> f.getFollowing().getProfile().getUsername().equals(targetUsername));

        return ResponseProfile.of(targetUser.getProfile(), matchResult);
    }

    @Transactional
    public ResponseUser update(String userId, RequestUpdateUser requestUpdateUser) {
        checkUnique(requestUpdateUser);
        Users user = usersRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        user.update(requestUpdateUser);

        return ResponseUser.from(user);
    }

    private void checkUnique(RequestUpdateUser requestUpdateUser) {
        boolean emailNotUnique = usersRepository.findByEmail(requestUpdateUser.getEmail()).isPresent();
        boolean usernameNotUnique = usersRepository.findByProfileUsername(requestUpdateUser.getUsername()).isPresent();

        if(emailNotUnique)
            throw new BusinessException(ErrorCode.EMAIL_NOT_UNIQUE);

        if(usernameNotUnique)
            throw new BusinessException(ErrorCode.USERNAME_NOT_UNIQUE);
    }
}
