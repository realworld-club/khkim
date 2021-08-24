package com.realworld.project.user.application;

import com.google.common.base.Preconditions;
import com.realworld.project.user.api.UserResponse;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(User user) {
        checkNotNull(user.getUsername());
        checkNotNull(user.getPassword());

        User userEntity = userRepository.save(user);

        return new UserResponse(userEntity);
    }
}
