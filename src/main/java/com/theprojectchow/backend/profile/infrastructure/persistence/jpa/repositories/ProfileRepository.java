package com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.profile.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id);
    List<Profile> findByStatus(String status);
}
