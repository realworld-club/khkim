package com.realworld.project.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.Key;

/**
 * https://github.com/jwtk/jjwt#install-jdk-gradle
 */
public class JwtTest {

    @DisplayName("Quickstart" +
            "1. building a JWT that will have the registered claim sub (subject) set to Joe. We are then" +
            "2. signing the JWT using a key suitable for the HMAC-SHA-256 algorithm. Finally, we are" +
            "3. compacting it into its final String form. A signed JWT is called a 'JWS'" +
            "Now let's verify the JWT (you should always discard JWTs that don't match an expected signature):"
    )
    @Test
    void jwtBuilder() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        //A signed jwt = jws (short for signed JWT)
        String jws = Jwts.builder()
                .setSubject("Joe")
                .signWith(key)
                .compact();

        System.out.println("jws = " + jws);

        //verify
        assert Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody()
                .getSubject()
                .equals("Joe");

    }
}
