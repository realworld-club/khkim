package com.realworld.project.user.domain;

import com.realworld.project.user.domain.Profile;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
@ToString
@EqualsAndHashCode
public class User {

    @Id @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    private String token;

    //TODO Embedded 의 장점을 잘 못살리고있다
    @Embedded
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "follow_user",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "user_id")
    )
    private List<User> follow = new ArrayList<>();

    @Builder
    public User(String username, String password, String email, String token, Profile profile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
        this.profile = (profile != null) ? profile : new Profile();
    }

    public void encoder(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void following(User user) {
        this.follow.add(user);
        this.profile.follow();
    }

    public void unfollow(User user) {
        this.follow.remove(user);
        this.profile.unfollow();
    }

    public void statusIsFollow() {
        getProfile().follow();
    }
}
