package com.realworld.project.user.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.project.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class UserModel {

    UserModelNested user;

    public static UserModel fromEntity(User user) {
        return new UserModel(UserModelNested.generator(user));
    }

    @Value
    private static class UserModelNested {
        String email;
        String token;
        String username;
        String bio;
        String image;

        private static UserModelNested generator(User user) {
            return new UserModelNested(
                    user.getEmail(),
                    user.getToken(),
                    user.getUsername(),
                    user.getProfile().getBio(),
                    user.getProfile().getImage());
        }
    }
}
