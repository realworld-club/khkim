package com.realworld.project.user.service;

import com.realworld.project.user.api.dto.RequestLoginUser;
import com.realworld.project.user.api.dto.ResponseUser;
import com.realworld.project.user.domain.Users;
import com.realworld.project.user.domain.UsersRepository;
import com.realworld.project.user.infra.jwt.JwtTokenProvider;
import com.realworld.project.utils.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.realworld.project.utils.exception.ErrorCode.USER_NOT_FOUND;

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
