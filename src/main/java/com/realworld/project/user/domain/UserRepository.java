package com.realworld.project.user.domain;

import com.realworld.project.user.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
