package com.realworld.project.application.user.api;

import com.realworld.project.application.user.api.dto.RequestLoginUser;
import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.api.dto.ResponseUser;
import com.realworld.project.application.user.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CredentialApi {

    private final CredentialService credentialService;

    /**
     * 사용자 로그인
     *
     * @param requestLoginUser 사용자 로그인 정보
     * @return 사용자정보
     */
    @PostMapping("/api/users/login")
    public ResponseEntity<ResponseUser> login(@Valid @RequestBody RequestLoginUser requestLoginUser) {

        ResponseUser responseUser = credentialService.login(requestLoginUser);

        return ResponseEntity.ok(responseUser);
    }


    /**
     * 회원가입
     *
     * @param requestRegisterUser 가입정보
     * @return 사용자정보
     */
    @PostMapping("api/users")
    public ResponseEntity<ResponseUser> registration(@Valid @RequestBody RequestRegisterUser requestRegisterUser) {

        ResponseUser responseUser = credentialService.registerUsers(requestRegisterUser);

        return ResponseEntity.ok(responseUser);
    }

}
