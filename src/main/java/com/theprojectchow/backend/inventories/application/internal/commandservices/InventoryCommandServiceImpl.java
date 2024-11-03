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
    //@Transactional
    @Override
    public Long handle(CreateInventoryCommand command) {
        // Crear una nueva instancia de Inventory con una lista vacía de materiales
        //Inventory inventory = new Inventory(command.getId(), command.getName(), List.of());
        //Inventory inventory = new Inventory(command.getName(), List.of());
        if (inventoryRepository.existsByStandName(command.standName())) {
            throw new IllegalArgumentException("Inventario con el mismo nombre ya existe");
        }
        var inventory = new Inventory(command);
        try {
            // Guardar el inventario en la base de datos
            inventoryRepository.save(inventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el inventario: " + e.getMessage());
        }
        return inventory.getId();

        // Guardar el inventario en la base de datos
        //inventoryRepository.save(inventory);
    }

    // Método para manejar la eliminación de un inventario
    @Transactional
    //@Override
    public void handle(DeleteInventoryCommand command) {
        if (!inventoryRepository.existsById(command.inventoryId())) {
            throw new IllegalArgumentException("Inventario no encontrado");
        }
        try {
            inventoryRepository.deleteById(command.inventoryId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el inventario: " + e.getMessage());
        }


        /*
        // Buscar el inventario por ID
        Inventory inventory = inventoryRepository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Inventario con ID " + command.getId() + " no encontrado"));

        // Eliminar el inventario
        inventoryRepository.delete(inventory);*/
    }
}
