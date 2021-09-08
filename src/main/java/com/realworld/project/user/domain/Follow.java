package com.realworld.project.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "follow")
@ToString
@EqualsAndHashCode
public class Follow {
    @Id
    @Column(name = "follow_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_user_id")
    private User followUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_user_id")
    private User followeeUser;


    public Follow(User followUser, User followeeUser) {
        this.followUser = followUser;
        this.followeeUser = followeeUser;
    }
}
