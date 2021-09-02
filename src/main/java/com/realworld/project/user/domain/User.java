package com.realworld.project.user.domain;

import com.realworld.project.user.domain.Profile;
import lombok.*;

import javax.persistence.*;

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

    @Embedded
    private Profile profile;

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

    public void changeProfile(Profile profile) {
        this.profile.changeImage(profile.getImage());
        this.profile.changeBio(profile.getBio());
    }
}
