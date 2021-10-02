package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.api.dto.RequestUpdateUser;
import com.realworld.project.application.user.api.dto.ResponseProfile;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserApi {

    private final UserService userService;

    /**
     * 사용자정보 요청
     *
     * @return 사용자정보
     */
    @GetMapping("/api/user")
    public ResponseEntity<ResponseUser> getUser(Principal principal) {

        ResponseUser responseUser = userService.getUserInfo(principal.getName());

        return ResponseEntity.ok(responseUser);
    }

    /**
     * 사용자 정보 갱신
     *
     * @return 사용자정보
     */
    @PutMapping("/api/user")
    public ResponseEntity<ResponseUser> updateUser(
            Principal principal,
            @Valid @RequestBody RequestUpdateUser requestUpdateUser) {

        ResponseUser responseUser = userService.update(principal.getName(), requestUpdateUser);

        return ResponseEntity.ok(responseUser);
    }

    /**
     * 프로필정보 요청
     *
     * @param username 대상유저
     * @return 프로필
     */
    @GetMapping("/api/profiles/{username}")
    public ResponseEntity<ResponseProfile> getProfile(@PathVariable("username") String username) {

        ResponseProfile responseProfile = userService.getProfile(username);

        return ResponseEntity.ok(responseProfile);
    }

}
