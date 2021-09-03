package com.realworld.project.user.application;

import com.realworld.project.user.api.dto.UserUpdateRequest;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import com.realworld.project.util.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UpdateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User updateUser(UserUpdateRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(InvalidRequestException::new);

        Optional.ofNullable(request.getEmail())
                .ifPresent(e -> updateEmail(e, user));
        Optional.ofNullable(request.getUsername())
                .ifPresent(u ->updateUsername(u, user));
        Optional.ofNullable(request.getBio())
                .ifPresent(b ->updateBio(b, user));
        Optional.ofNullable(request.getPassword())
                .ifPresent(p ->updatePassword(p, user));
        Optional.ofNullable(request.getImage())
                .ifPresent(i ->updateImage(i, user));

        return user;
    }

    private void updateImage(String image, User user) {
        if(image.equals(user.getProfile().getImage()))
            return;

        user.getProfile().changeImage(image);
    }


    private void updatePassword(String password, User user) {
        if(comparePassword(password, user.getPassword()))
            return;

        user.changePassword(passwordEncoder.encode(password));
    }

    private boolean comparePassword(String password, String targetPassword) {
        String encode = passwordEncoder.encode(password);
        int i = encode.compareTo(targetPassword);
        if(i == 0)
            return true;

        return false;
    }

    private void updateBio(String bio, User user) {
        if(bio.equals(user.getProfile().getBio()))
            return;

        user.getProfile().changeBio(bio);
    }

    private void updateUsername(String username, User user) {
        if(username.equals(user.getUsername()))
            return;

        userRepository.existsByUsername(username)
                .ifPresent(aBoolean -> {
                    if(!aBoolean) user.changeUsername(username);
                });

    }

    private void updateEmail(String email, User user) {
        if(email.equals(user.getEmail()))
            return;

        userRepository.existsByEmail(email)
                .ifPresent(aBoolean -> {
                    if(!aBoolean) user.changeEmail(email);
                });
    }
}
