package com.theprojectchow.backend.inventories.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public record DeleteInventoryCommand(Long inventoryId) {
}

/*
// Comando para eliminar un inventario
@Getter
@AllArgsConstructor // Constructor con todos los argumentos (en este caso solo el ID)
@ToString // Método toString para facilitar la depuración
public class DeleteInventoryCommand {
    private final String id; // ID del inventario a eliminar
}*/
