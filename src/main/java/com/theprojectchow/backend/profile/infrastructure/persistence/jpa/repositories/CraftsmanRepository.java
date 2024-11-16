package com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman, Long> {
    Optional<Craftsman> findByEmail(EmailAddress emailAddress);
    Optional<Craftsman> findByProfileId(ProfileId profileId);

}
