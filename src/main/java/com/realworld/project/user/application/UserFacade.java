package com.realworld.project.user.application;

import com.realworld.project.user.api.dto.UserLoginRequest;
import com.realworld.project.user.api.dto.UserModel;
import com.realworld.project.user.api.dto.UserRegisterRequest;
import com.realworld.project.user.api.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserFacade {

    private final CredentialService credentialService;
    private final DeleteService deleteService;
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

    @Transactional
    public void delete(String username) {
        deleteService.delete(username);
    }
}
