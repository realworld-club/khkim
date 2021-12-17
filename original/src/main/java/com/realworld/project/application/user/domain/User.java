package com.realworld.project.application.user.domain;

import com.realworld.project.application.article.domain.Article;
import com.realworld.project.application.user.api.dto.RequestRegisterUser;
import com.realworld.project.application.user.api.dto.RequestUpdateUser;
import com.realworld.project.application.user.service.PasswordHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {

    @Id @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<Follow> following = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteUsers")
    private Set<Article> articles = new HashSet<>();

    @Embedded
    private Profile profile;

    @Builder
    public User(String email, String password, Profile profile) {
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public static User from(RequestRegisterUser registerUser) {
        return User.builder()
                .email(registerUser.getEmail())
                .password(PasswordHelper.encode(registerUser.getPassword()))
                .profile(new Profile(registerUser.getUsername()))
                .build();
    }

    public void update(RequestUpdateUser user) {
        if(StringUtils.hasText(user.getUsername()))
            profile.updateUsername(user.getUsername());

        if(StringUtils.hasText(user.getBio()))
            profile.updateBio(user.getBio());

        if(StringUtils.hasText(user.getImage()))
            profile.changeImage(user.getImage());

        if(StringUtils.hasText(user.getEmail()))
            this.email = user.getEmail();

        if(StringUtils.hasText(user.getPassword()))
            this.password = PasswordHelper.encode(user.getPassword());
    }
}
