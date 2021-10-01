package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.domain.Users;
import com.realworld.project.application.user.repository.UsersRepository;
import com.realworld.project.core.jwt.JwtTokenProvider;
import com.realworld.project.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseUser login(RequestLoginUser requestLoginUser) {
        Users user = usersRepository.findByEmail(requestLoginUser.getEmail())
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        String token = jwtTokenProvider.createToken(Long.toString(user.getId()), null);

        return ResponseUser.of(user, token);
    }
}
