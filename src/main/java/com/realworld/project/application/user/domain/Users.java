package com.realworld.project.application.user.domain;

import com.realworld.project.application.user.api.dto.RequestUpdateUser;
import com.realworld.project.application.user.service.PasswordHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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
