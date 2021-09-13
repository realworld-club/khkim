package com.realworld.project.fixture;

import com.realworld.project.user.domain.Follow;
import com.realworld.project.user.domain.User;

public class FollowFixture {
    public static Follow ofEntity() {
        return new Follow(UserFixture.ofEntity(), UserFixture.ofNewEntity());
    }
}
