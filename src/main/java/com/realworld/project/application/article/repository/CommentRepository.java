package com.realworld.project.application.article.repository;

import com.realworld.project.application.article.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
