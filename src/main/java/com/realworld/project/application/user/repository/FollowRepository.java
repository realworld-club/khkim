package com.realworld.project.application.user.repository;

import com.realworld.project.application.user.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
