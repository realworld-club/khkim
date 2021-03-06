package com.realworld.project.application.user.service;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.jwt.JwtTokenProvider;
import com.realworld.project.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CredentialService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseUser login(RequestLoginUser requestLoginUser) {
        User user = userRepository.findByEmail(requestLoginUser.getEmail())
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        String token = jwtTokenProvider.createToken(Long.toString(user.getId()), null);

        return ResponseUser.of(user, token);
    }

    @Transactional
    public ResponseUser registerUsers(RequestRegisterUser requestRegisterUser) {
        User entity = User.from(requestRegisterUser);
        User user = userRepository.save(entity);

        return ResponseUser.from(user);
    }
}
