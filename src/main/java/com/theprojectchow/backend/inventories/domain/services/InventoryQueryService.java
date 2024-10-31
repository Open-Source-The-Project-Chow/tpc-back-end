package com.theprojectchow.backend.inventories.domain.services;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllInventorysQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetInventoryByIdQuery;

import java.util.List;
import java.util.Optional;

// Interfaz del servicio de consultas para Inventory
public interface InventoryQueryService {

    // Método para obtener todos los inventarios
    List<Inventory> handle(GetAllInventorysQuery query);

    // Método para obtener un inventario por ID
    Optional<Inventory> handle(GetInventoryByIdQuery query);
}
