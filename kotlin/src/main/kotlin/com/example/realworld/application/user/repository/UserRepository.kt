package com.example.realworld.application.user.repository

import com.example.realworld.application.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

}