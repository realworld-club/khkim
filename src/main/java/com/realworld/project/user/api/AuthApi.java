package com.realworld.project.user.api;

import com.realworld.project.user.dto.RequestLoginUser;
import com.realworld.project.user.dto.ResponseUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthApi {

    /**
     * 사용자 로그인
     *
     * @param requestLoginUser 사용자 로그인 정보
     * @return 사용자정보
     */
    @PostMapping("/api/users/login")
    public ResponseEntity<ResponseUser> login(@Valid @RequestBody RequestLoginUser requestLoginUser) {

        ResponseUser responseUser = null;

        return ResponseEntity.ok(responseUser);
    }



}
