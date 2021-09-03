package com.realworld.project.user.api;

import com.realworld.project.user.api.dto.ProfileModel;
import com.realworld.project.user.api.wrapper.ProfileModelWrapper;
import com.realworld.project.user.application.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    //TODO API Spec Authentication optional ??
    @GetMapping("/profiles/{username}")
    public ProfileModelWrapper<ProfileModel> getProfile
            (@PathVariable String username, Principal principal) {
        return new ProfileModelWrapper<>(
                ProfileModel.fromEntity(profileService.getProfile(username, principal.getName())));
    }

    @PostMapping("/profiles/{username}/follow")
    public ProfileModelWrapper<ProfileModel> followUser
            (@PathVariable String username, Principal principal) {
        return new ProfileModelWrapper<>(
                ProfileModel.fromEntity(profileService.follow(username, principal.getName())));
    }

    @DeleteMapping("/profiles/{username}/follow")
    public ProfileModelWrapper<ProfileModel> unFollowUser
            (@PathVariable String username, Principal principal) {
        return new ProfileModelWrapper<>(
                ProfileModel.fromEntity(profileService.unFollow(username, principal.getName())));
    }
}
