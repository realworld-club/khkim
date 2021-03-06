package com.realworld.project.application.user.service;

import com.realworld.project.application.user.domain.User;
import com.realworld.project.application.user.repository.UserRepository;
import com.realworld.project.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.realworld.project.core.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.EMPTY_LIST);
    }

}
