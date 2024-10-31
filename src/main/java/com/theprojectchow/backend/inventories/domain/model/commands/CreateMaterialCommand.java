package com.theprojectchow.backend.inventories.domain.model.commands;

public record CreateMaterialCommand(String name, int quantity, String stand){}