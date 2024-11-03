package com.theprojectchow.backend.inventories.domain.services;

import com.theprojectchow.backend.inventories.domain.model.commands.CreateInventoryCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteInventoryCommand;

// Interfaz del servicio de comandos para Inventory
public interface InventoryCommandService {

    // Método para manejar la creación de un inventario
    Long handle(CreateInventoryCommand command);

    // Método para manejar la eliminación de un inventario
    void handle(DeleteInventoryCommand command);
}
