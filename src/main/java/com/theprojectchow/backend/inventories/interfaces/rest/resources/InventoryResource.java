package com.theprojectchow.backend.inventories.interfaces.rest.resources;

import java.util.List;

public record InventoryResource(String id, String standName, List<MaterialResource> materials) {
}
