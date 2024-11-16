package com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import com.theprojectchow.backend.profile.domain.model.valueobjects.UserId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Craftsman c WHERE c.profile.email = :name")
    boolean existsByEmail(@Param("name") String name);

    Optional<Craftsman> findByUserId(UserId userId);

    //Optional<Craftsman> findById(Long craftsmanId);

    /*
    Optional<Craftsman> findByEmail(EmailAddress emailAddress);
    Optional<Craftsman> findByProfileId(ProfileId profileId);*/

}
