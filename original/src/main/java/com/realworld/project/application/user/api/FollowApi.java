package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class FollowApi {

    private final FollowService followService;

    /**
     * follow 기능
     *
     * @param username follow 대상유저
     * @return 프로필
     */
    @PostMapping("/api/profiles/{username}/follow")
    public ResponseEntity<ResponseProfile> follow(
            Principal principal,
            @PathVariable("username") String username) {

        ResponseProfile responseProfile = followService.follow(principal.getName(), username);

        return ResponseEntity.ok(responseProfile);
    }

    /**
     * follow 취소
     *
     * @param username unfollow 대상유저
     * @return 프로필
     */
    @DeleteMapping("/api/profiles/{username}/follow")
    public ResponseEntity<ResponseProfile> unFollow(
            Principal principal,
            @PathVariable("username") String username) {

        ResponseProfile responseProfile = null;

        return ResponseEntity.ok(responseProfile);
    }

}
