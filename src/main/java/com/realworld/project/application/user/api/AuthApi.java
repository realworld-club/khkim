package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthApi {

    private final AuthService authService;

    /**
     * 사용자 로그인
     *
     * @param requestLoginUser 사용자 로그인 정보
     * @return 사용자정보
     */
    @PostMapping("/api/users/login")
    public ResponseEntity<ResponseUser> login(@Valid @RequestBody RequestLoginUser requestLoginUser) {

        ResponseUser responseUser = authService.login(requestLoginUser);

        return ResponseEntity.ok(responseUser);
    }



}
