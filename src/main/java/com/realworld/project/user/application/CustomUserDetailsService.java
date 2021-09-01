package com.realworld.project.user.application;

import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //보안으로 username 이 아닌 id 로 token 을 packing 하였기 때문에 parsing 해준다
        User user = userRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new RuntimeException("exception"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.emptyList());
    }

}
