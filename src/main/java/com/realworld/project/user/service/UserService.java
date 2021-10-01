package com.realworld.project.user.service;

import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.api.dto.ResponseUser;
import com.realworld.project.user.domain.Users;
import com.realworld.project.user.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;

    public ResponseUser registerUsers(RequestRegisterUser requestRegisterUser) {
        Users user = usersRepository.save(requestRegisterUser.toEntity());

        return ResponseUser.of(user);
    }
}
