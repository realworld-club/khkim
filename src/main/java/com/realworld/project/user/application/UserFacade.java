package com.realworld.project.user.application;

import com.realworld.project.user.api.UserLoginRequest;
import com.realworld.project.user.api.UserModel;
import com.realworld.project.user.api.UserRegisterRequest;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final CredentialService credentialService;

    public UserModel login(UserLoginRequest request) {
        return UserModel.fromEntity(credentialService.login(request.toEntity()));
    }

    public UserModel register(UserRegisterRequest request) {
        return UserModel.fromEntity(credentialService.register(request.toEntity()));
    }




}
