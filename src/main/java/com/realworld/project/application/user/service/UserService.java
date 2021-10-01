package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.*;
import com.realworld.project.application.user.domain.Users;
import com.realworld.project.application.user.repository.UsersRepository;
import com.realworld.project.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ResponseUser registerUsers(RequestRegisterUser requestRegisterUser) {
        Users user = usersRepository.save(requestRegisterUser.toEntity());

        return ResponseUser.from(user);
    }


    public ResponseProfile getProfile(String username) {
        Users users = usersRepository.findByProfileUsername(username)
                .orElseThrow(() -> new BusinessException(USER_PROFILE_NOT_FOUND));

        return ResponseProfile.from(users.getProfile());
    }

    @Transactional
    public ResponseUser update(String userId, RequestUpdateUser requestUpdateUser) {
        Users user = usersRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));



        return ResponseUser.from(user);
    }
}
