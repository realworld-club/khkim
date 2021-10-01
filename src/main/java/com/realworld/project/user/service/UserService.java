package com.realworld.project.user.service;

import com.realworld.project.user.api.dto.RequestLoginUser;
import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.api.dto.ResponseProfile;
import com.realworld.project.user.api.dto.ResponseUser;
import com.realworld.project.user.domain.Users;
import com.realworld.project.user.domain.UsersRepository;
import com.realworld.project.utils.exception.BusinessException;
import com.realworld.project.utils.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.utils.exception.ErrorCode.USER_NOT_FOUND;
import static com.realworld.project.utils.exception.ErrorCode.USER_PROFILE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;

    public ResponseUser getUserInfo(String userId) {
        Users user = usersRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        return ResponseUser.of(user);
    }

    @Transactional
    public ResponseUser registerUsers(RequestRegisterUser requestRegisterUser) {
        Users user = usersRepository.save(requestRegisterUser.toEntity());

        return ResponseUser.of(user);
    }


    public ResponseProfile getProfile(String username) {
        Users users = usersRepository.findByProfileUsername(username)
                .orElseThrow(() -> new BusinessException(USER_PROFILE_NOT_FOUND));

        return ResponseProfile.from(users.getProfile());
    }
}
