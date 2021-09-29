package com.realworld.project.user.api;

import com.realworld.project.user.dto.ResponseProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowApi {

    /**
     * follow 기능
     *
     * @param username follow 대상유저
     * @return 프로필
     */
    @PostMapping("/api/profiles/{username}/follow")
    public ResponseEntity<ResponseProfile> follow(@PathVariable("username") String username) {

        ResponseProfile responseProfile = null;

        return ResponseEntity.ok(responseProfile);
    }

    /**
     * follow 취소
     *
     * @param username unfollow 대상유저
     * @return 프로필
     */
    @DeleteMapping("/api/profiles/{username}/follow")
    public ResponseEntity<ResponseProfile> unFollow(@PathVariable("username") String username) {

        ResponseProfile responseProfile = null;

        return ResponseEntity.ok(responseProfile);
    }

}
