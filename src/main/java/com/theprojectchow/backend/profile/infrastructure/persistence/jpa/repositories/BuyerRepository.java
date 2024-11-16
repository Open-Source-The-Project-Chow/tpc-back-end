package com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Optional<Buyer> findByEmail(EmailAddress emailAddress);
    Optional<Buyer> findByProfileId(ProfileId profileId);
}