package com.realworld.project.user;

import com.realworld.project.fixture.JwtFixture;
import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.application.UpdateService;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import com.sun.xml.internal.ws.spi.db.FieldSetter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.realworld.project.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UpdateServiceTest {

    @Mock
    UserRepository userRepository;

    @Spy
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    UpdateService updateService;

    @Test
    void 유저_업데이트_테스트() {
        //given
        given(userRepository.findByUsername(any())).willReturn(Optional.of(ofEntity()));
        given(userRepository.existsByUsername(new_username)).willReturn(Optional.of(false));
        given(userRepository.existsByEmail(new_email)).willReturn(Optional.of(false));
        //when
        User user = updateService.updateUser(ofUpdateRequest(), username);
        //then
        assertThat(user).usingRecursiveComparison()
                .ignoringFields("password")
                .isEqualTo(ofNewEntity());

        //bcryption 결과 string 은 matches 를 통해 검증할 수 있다
        boolean matches = passwordEncoder.matches(new_password, user.getPassword());
        assertThat(matches).isTrue();
    }

}
