package com.realworld.project.user.domain;

import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowUserIdAndFolloweeUserId(Long followUser_id, Long followeeUser_id);

    List<Follow> findByFollowUserIdOrFolloweeUserId(Long id, Long id1);
}
