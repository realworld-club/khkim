package com.example.realworld.application.user.service

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import com.example.realworld.application.user.api.dto.ResponseUser
import com.example.realworld.application.user.domain.User
import com.example.realworld.application.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CredentialService(
    @Autowired val userRepository: UserRepository) {

    fun registerUsers(requestRegisterUser: RequestRegisterUser): ResponseUser {
        val entity: User = User.from(requestRegisterUser)
        val user = userRepository.save(entity)

        return ResponseUser.from(user)
    }
}