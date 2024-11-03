package com.theprojectchow.backend.inventories.application.internal.queryservices;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllInventorysQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetInventoryByIdQuery;
import com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.theprojectchow.backend.inventories.domain.services.InventoryQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {

    //private static final Logger logger = LoggerFactory.getLogger(InventoryQueryServiceImpl.class); // Agrega Logger

    private final InventoryRepository inventoryRepository;

    // Constructor para inyectar el repositorio
    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> handle(GetAllInventorysQuery query) {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> handle(GetInventoryByIdQuery query) {
        return inventoryRepository.findById(query.inventoryId());
    }

    /*// Maneja la consulta para obtener todos los inventarios
    @Override
    public List<Inventory> handle(GetAllInventorysQuery query) {
        logger.info("Consultando todos los inventarios");
        return inventoryRepository.findAll(); // Retorna todos los inventarios (puede optimizarse más si agregas .orElse(List.of()) si existe la posibilidad de recibir null)
    }

    // Maneja la consulta para obtener inventario por ID
    @Override
    public Optional<Inventory> handle(GetInventoryByIdQuery query) {
        logger.info("Consultando inventario con ID: {}", query.getId());

        return inventoryRepository.findById(query.getId())
                .or(() -> {
                    logger.warn("Inventario con ID {} no encontrado", query.getId());
                    return Optional.empty(); // Evita retornar null, opción más segura
                });
    }*/
}
