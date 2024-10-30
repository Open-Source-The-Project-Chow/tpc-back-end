package com.theprojectchow.backend.inventories.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// Comando para crear un inventario
@Getter
@ToString // Para depuración rápida
public class CreateInventoryCommand {
    private final Long id;  // Cambiado a Long
    private final String name;

    public CreateInventoryCommand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}