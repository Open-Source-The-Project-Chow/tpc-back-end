package com.theprojectchow.backend.inventories.interfaces.rest.transform;

import com.theprojectchow.backend.inventories.domain.model.commands.UpdateMaterialCommand;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.UpdateMaterialResource;

public class UpdateMaterialCommandFromResourceAssembler {
    public static UpdateMaterialCommand toCommandFromResource(Long materialId, UpdateMaterialResource resource) {
        return new UpdateMaterialCommand(materialId, resource.name(), resource.quantity(), resource.stand());
    }
}
