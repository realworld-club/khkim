package com.realworld.project.user.api;

import com.realworld.project.user.api.dto.RequestRegisterUser;
import com.realworld.project.user.api.dto.RequestUpdateUser;
import com.realworld.project.user.api.dto.ResponseProfile;
import com.realworld.project.user.api.dto.ResponseUser;
import com.realworld.project.user.service.UserService;
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
     * 회원가입
     *
     * @param requestRegisterUser 가입정보
     * @return 사용자정보
     */
    @PostMapping("api/users")
    public ResponseEntity<ResponseUser> registration(@Valid @RequestBody RequestRegisterUser requestRegisterUser) {

        ResponseUser responseUser = userService.registerUsers(requestRegisterUser);

        return ResponseEntity.ok(responseUser);
    }

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
    public ResponseEntity<ResponseUser> updateUser(@Valid @RequestBody RequestUpdateUser requestUpdateUser) {

        ResponseUser responseUser = null;

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
