package com.example.realworld.core.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {
    @Value("\${jwt.base64.secretKey}")
    private val secretKey: String? = null

    // 토큰 유효시간 30분
    private val tokenValidTime = 30 * 60 * 1000L

    // 토큰의 유효성 + 만료일자 확인
    fun validateToken(jwtToken: String?): Boolean {
            throw Exception()
//        return
//            val claims: Jws<Claims> = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
//            !claims.getBody().getExpiration().before(Date())
    }
}