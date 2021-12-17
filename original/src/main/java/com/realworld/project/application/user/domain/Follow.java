package com.realworld.project.application.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "follow")
public class Follow {

    @Id @Column(name = "follow_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_user_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id")
    private User following;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
        follower.getFollowing().add(this);
    }

    public void following() {
        follower.getFollowing().add(this);
    }

    public String followingUserName() {
        return this.getFollowing().getProfile().getUsername();
    }
}
