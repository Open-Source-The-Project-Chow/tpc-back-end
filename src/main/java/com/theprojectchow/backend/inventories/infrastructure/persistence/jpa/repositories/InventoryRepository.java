package com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    // Puedes definir métodos adicionales si es necesario
}
