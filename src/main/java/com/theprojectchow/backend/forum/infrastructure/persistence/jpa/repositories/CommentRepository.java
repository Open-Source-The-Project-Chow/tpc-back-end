package com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.forum.domain.model.aggregates.Comment;
import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    boolean existsByContent(String content);

    List<Comment> findByPostId(Long postId);
}
