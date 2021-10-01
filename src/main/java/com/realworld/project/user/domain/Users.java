package com.realworld.project.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class Users {

    @Id @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @Embedded
    private Profile profile;

    @Builder
    public Users(String email, String password, Profile profile) {
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

}
