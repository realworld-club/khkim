package com.example.realworld.application.user.repository

import com.example.realworld.application.user.domain.User
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserRepositoryTest (
    @Autowired val userRepository: UserRepository
    ) {

    @Test
    fun `jpa 테스트`() {
        //given
        val user = User(email = "email", password = "password")
        //when
        userRepository.save(user);
        //then
        val userEntity = userRepository.findAll()
        assertThat(userEntity[0].email).isEqualTo("email")
    }
}