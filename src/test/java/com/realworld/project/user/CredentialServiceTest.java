package com.realworld.project.user;

import com.realworld.project.fixture.JwtFixture;
import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.application.CredentialService;
import com.realworld.project.user.application.UpdateService;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import com.realworld.project.user.infra.jwt.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CredentialServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Spy
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    CredentialService credentialService;

    @Test
    void 등록_테스트() {
        //given
        given(userRepository.save(any())).willReturn(ofEntity());
        //when
        User user = credentialService.register(ofRegisterRequest());
        //then
        assertUser(user);
    }

    @Test
    void 로그인_테스트() {
        //given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(ofEntity()));
        given(jwtTokenProvider.createToken(any(), any())).willReturn(getToken());
        //when
        User user = credentialService.login(ofLoginRequest());
        //then
        assertUser(user);
    }

    @Test
    void 현재유저_테스트() {
        //given
        given(userRepository.findByUsername(any())).willReturn(Optional.of(ofEntity()));
        //when
        User user = credentialService.getCurrentUser(username);
        //then
        assertUser(user);
    }

    private void assertUser(User user) {
        assertThat(user).usingRecursiveComparison()
                .ignoringFields("password")
                .isEqualTo(ofEntity());

        //bcryption 결과 string 은 matches 를 통해 검증할 수 있다
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        assertThat(matches).isTrue();
    }
}
