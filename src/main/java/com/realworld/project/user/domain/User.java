package com.realworld.project.user.domain;

import com.realworld.project.user.domain.Profile;
import lombok.*;
import org.hibernate.annotations.Cascade;

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

    private String bio;
    private String image;
    @Transient
    private boolean following;

    @Builder
    public User(String username, String password, String email, String token, String bio, String image, boolean following) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
        this.bio = bio;
        this.image = image;
        this.following = following;
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

    public void followingTrue() {
        this.following = true;
    }

    public void changeImage(String image) {
        this.image = image;
    }

    public void changeBio(String bio) {
        this.bio = bio;
    }
}
