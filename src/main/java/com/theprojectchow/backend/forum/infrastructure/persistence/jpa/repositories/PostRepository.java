package com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
    boolean existsByContent(String content);
}
