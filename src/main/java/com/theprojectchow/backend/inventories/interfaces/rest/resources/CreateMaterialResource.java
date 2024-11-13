package com.theprojectchow.backend.inventories.interfaces.rest.resources;

public record CreateMaterialResource(Long inventoryId,String name, int quantity, String stand) {
}
