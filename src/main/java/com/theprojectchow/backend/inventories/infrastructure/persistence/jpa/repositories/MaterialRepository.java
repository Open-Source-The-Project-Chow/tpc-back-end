package com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Optional<Material> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
}
