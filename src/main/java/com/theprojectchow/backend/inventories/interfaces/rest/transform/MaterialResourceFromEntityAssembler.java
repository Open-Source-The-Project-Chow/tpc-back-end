package com.theprojectchow.backend.inventories.interfaces.rest.transform;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.MaterialResource;

public class MaterialResourceFromEntityAssembler {
    public static MaterialResource toResourceFromEntity(Material entity) {
        return new MaterialResource(entity.getId(), entity.getName(), entity.getQuantity(), entity.getStand());
    }
}
