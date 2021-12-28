package com.example.realworld.application.user.api

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import com.example.realworld.application.user.api.dto.ResponseUser
import com.example.realworld.application.user.service.CredentialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CredentialApi(@Autowired val credentialService: CredentialService) {

    /**
     * 회원가입
     *
     * @param requestRegisterUser 가입정보
     * @return 사용자정보
     */
    @PostMapping("/api/users")
    fun registration(@Valid @RequestBody requestRegisterUser: RequestRegisterUser): ResponseEntity<ResponseUser> {

        val responseUser = credentialService.registerUsers(requestRegisterUser)

        return ResponseEntity.ok(responseUser)
    }
}