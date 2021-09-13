package com.realworld.project.user.application;

import com.realworld.project.fixture.FollowFixture;
import com.realworld.project.fixture.UserFixture;
import com.realworld.project.user.application.ProfileService;
import com.realworld.project.user.domain.Follow;
import com.realworld.project.user.domain.FollowRepository;
import com.realworld.project.user.domain.User;
import com.realworld.project.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static com.realworld.project.fixture.UserFixture.*;
import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProfileServiceTest {

    private final String following = username;
    private final String follower = new_username;

    @Mock
    UserRepository userRepository;

    @Mock
    FollowRepository followRepository;

    @InjectMocks
    ProfileService profileService;

    @Test
    void follow_테스트() {
        //given
        userRepositoryStubbing();
        //when
        User user = profileService.follow(following, follower);
        //then
        assertThat(user.isFollowing()).isTrue();
    }

    @Test
    void follow유저의_profile_테스트() {
        //given
        userRepositoryStubbing();
        followRepositoryPresent();
        //when
        User user = profileService.getProfile(following, follower);
        //then
        assertThat(user.isFollowing()).isTrue();
    }

    @Test
    void follow하지않은_유저의_profile_테스트() {
        //given
        userRepositoryStubbing();
        followRepositoryNothing();
        //when
        User user = profileService.getProfile(following, follower);
        //then
        assertThat(user.isFollowing()).isFalse();
    }

    @Test
    void unfollow_테스트() {
        //given
        userRepositoryStubbing();
        followRepositoryPresent();
        //when
        User user = profileService.unFollow(following, follower);
        //then
        assertThat(user.isFollowing()).isFalse();
    }

    private void userRepositoryStubbing() {
        given(userRepository.findByUsername(follower)).willReturn(of(ofNewEntity()));
        given(userRepository.findByUsername(following)).willReturn(of(ofEntity()));
    }

    private void followRepositoryNothing() {
        given(followRepository.findByFollowUserIdAndFolloweeUserId(any(), any()))
                .willReturn(Optional.empty());
    }

    private void followRepositoryPresent() {
        given(followRepository.findByFollowUserIdAndFolloweeUserId(any(), any()))
                .willReturn(of(FollowFixture.ofEntity()));
    }

}
