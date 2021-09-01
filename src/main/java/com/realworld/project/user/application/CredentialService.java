package com.realworld.project.user.application;

import com.realworld.project.user.api.UserLoginRequest;
import com.realworld.project.user.api.UserModel;
import com.realworld.project.user.api.UserRegisterRequest;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import com.realworld.project.user.infra.jwt.JwtTokenProvider;
import com.realworld.project.util.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class CredentialService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public User register(User user) {
        checkNotNull(user.getUsername());
        checkNotNull(user.getPassword());

        user.encoder(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User login(User user) {
        checkNotNull(user.getEmail());
        checkNotNull(user.getPassword());

        User userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(InvalidRequestException::new);

        String jws = jwtTokenProvider.createToken(String.valueOf(userEntity.getId()), null);
        userEntity.setToken(jws);

        return userEntity;
    }

    public User getCurrentUser(String name) {
        checkNotNull(name);

        User userEntity = userRepository.findByUsername(name)
                .orElseThrow(InvalidRequestException::new);

        return userEntity;
    }
}
