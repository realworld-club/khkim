package com.realworld.project.user.application;

import com.realworld.project.user.api.UserLoginRequest;
import com.realworld.project.user.api.UserModel;
import com.realworld.project.user.api.UserRegisterRequest;
import com.realworld.project.user.api.UserUpdateRequest;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserFacade {

    private final CredentialService credentialService;
    private final UpdateService updateService;

    public UserModel login(UserLoginRequest request) {
        return UserModel.fromEntity(credentialService.login(request));
    }

    @Transactional
    public UserModel register(UserRegisterRequest request) {
        return UserModel.fromEntity(credentialService.register(request));
    }

    public UserModel getCurrentUser(String name) {
        return UserModel.fromEntity(credentialService.getCurrentUser(name));
    }

    @Transactional
    public UserModel update(UserUpdateRequest request, String username) {
        return UserModel.fromEntity(updateService.updateUser(request, username));
    }
}
