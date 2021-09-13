package com.realworld.project.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<Boolean> existsByEmail(String email);

    Optional<User> findByUsername(String name);
    Optional<Boolean> existsByUsername(String username);

    long deleteByUsername(String username);
}
