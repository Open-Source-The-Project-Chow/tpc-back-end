package com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Puedes definir métodos adicionales si es necesario
    Optional<Inventory> findByStandName(String standName);
    boolean existsByStandName(String standName);
    boolean existsByStandNameAndIdIsNot(String standName, Long id);
}
