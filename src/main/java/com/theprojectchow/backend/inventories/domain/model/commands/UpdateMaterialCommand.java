package com.theprojectchow.backend.inventories.domain.model.commands;

public record UpdateMaterialCommand(Long id, String name, int quantity, String stand) {
}
