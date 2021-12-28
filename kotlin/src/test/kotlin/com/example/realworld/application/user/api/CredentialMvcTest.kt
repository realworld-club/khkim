package com.example.realworld.application.user.api

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import com.example.realworld.application.user.api.dto.ResponseUser
import com.example.realworld.application.user.service.CredentialService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CredentialApi::class)
class CredentialMvcTest(
    @Autowired var mockMvc: MockMvc) {

    @MockkBean lateinit var credentialService: CredentialService

    @Test
    fun `valid not empty 테스트`() {
        //given
        val requestRegisterUser = RequestRegisterUser("username", "email")
        val json = jacksonObjectMapper().writeValueAsString(requestRegisterUser)
        val responseUser = ResponseUser(email = "email")
        every { credentialService.registerUsers(requestRegisterUser) } answers { responseUser }

        //when
        val perform = mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
        //then
        perform
            .andExpect(status().`is`(400))
    }

}