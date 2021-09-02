package com.realworld.project.fixture;

import com.realworld.project.user.infra.jwt.JwtTokenProvider;
import org.springframework.test.util.ReflectionTestUtils;

public class JwtFixture {

    public static String crateToken(String userPk) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(null);

        ReflectionTestUtils.setField(
                jwtTokenProvider,
                "secretKey",
                "Y29tLnJlYWx3b3JsZC5wcm9qZWN0LnVzZXIuaW5mcmEuand0"
        );

        return jwtTokenProvider.createToken(userPk, null);
    }
}
