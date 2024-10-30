package com.theprojectchow.backend.inventories.application.internal.commandservices;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.commands.CreateInventoryCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteInventoryCommand;
import com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.theprojectchow.backend.inventories.domain.services.InventoryCommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository inventoryRepository;

    // Constructor para inyectar el repositorio
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Método para manejar la creación de un inventario
    @Override
    @Transactional
    public void handle(CreateInventoryCommand command) {
        // Crear una nueva instancia de Inventory con una lista vacía de materiales
        Inventory inventory = new Inventory(command.getId(), command.getName(), List.of());

        // Guardar el inventario en la base de datos
        inventoryRepository.save(inventory);
    }

    // Método para manejar la eliminación de un inventario
    @Override
    @Transactional
    public void handle(DeleteInventoryCommand command) {
        // Buscar el inventario por ID
        Inventory inventory = inventoryRepository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Inventario con ID " + command.getId() + " no encontrado"));

        // Eliminar el inventario
        inventoryRepository.delete(inventory);
    }
}
