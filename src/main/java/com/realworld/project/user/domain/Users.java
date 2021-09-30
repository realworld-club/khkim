package com.realworld.project.user.domain;

import lombok.AccessLevel;
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

    @Embedded
    private Profile profile;



}
