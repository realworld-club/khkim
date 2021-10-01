package com.realworld.project.user.infra;

import com.realworld.project.user.domain.Users;
import com.realworld.project.user.domain.UsersRepository;
import com.realworld.project.utils.exception.BusinessException;
import com.realworld.project.utils.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.realworld.project.utils.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Users user = usersRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        return new User(Long.toString(user.getId()), user.getPassword(), Collections.EMPTY_LIST);
    }

}
