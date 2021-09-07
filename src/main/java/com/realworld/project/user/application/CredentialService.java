package com.realworld.project.user.application;

import com.realworld.project.user.api.dto.UserLoginRequest;
import com.realworld.project.user.api.dto.UserRegisterRequest;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import com.realworld.project.user.infra.jwt.JwtTokenProvider;
import com.realworld.project.util.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@Component
public class CredentialService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public User register(UserRegisterRequest request) {
        checkNotNull(request.getUsername());
        checkNotNull(request.getPassword());

        User user = request.toEntity();
        user.encoder(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public User login(UserLoginRequest request) {
        checkNotNull(request.getEmail());
        checkNotNull(request.getPassword());

        User userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidRequestException::new);

        String jws = jwtTokenProvider.createToken(String.valueOf(userEntity.getId()), null);
        userEntity.setToken(jws);

        return userEntity;
    }

    public User getCurrentUser(String name) {
        checkNotNull(name);

        return userRepository.findByUsername(name)
                .orElseThrow(InvalidRequestException::new);
    }

    public boolean delete(String username) {
        userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);

        return userRepository.deleteByUsername(username) != 0;
    }
}
