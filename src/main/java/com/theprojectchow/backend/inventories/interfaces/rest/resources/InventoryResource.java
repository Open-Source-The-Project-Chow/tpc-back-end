package com.theprojectchow.backend.inventories.interfaces.rest.resources;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;

import java.util.List;

public record InventoryResource(Long id, String standName, List<MaterialResource> materials) {
}
