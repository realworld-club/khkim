package com.example.realworld.application.user.api

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import com.example.realworld.application.user.api.dto.ResponseUser
import com.example.realworld.application.user.service.CredentialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CredentialApi(@Autowired val credentialService: CredentialService) {

    @PostMapping("/api/users")
    fun registration(@Valid @RequestBody requestRegisterUser: RequestRegisterUser?): ResponseEntity<ResponseUser?>? {
        val responseUser: ResponseUser = credentialService.registerUsers(requestRegisterUser)
        return ResponseEntity.ok<ResponseUser?>(responseUser)
    }
}