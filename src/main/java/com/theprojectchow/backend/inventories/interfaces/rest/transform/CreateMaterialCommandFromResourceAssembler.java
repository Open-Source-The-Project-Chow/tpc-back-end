package com.theprojectchow.backend.inventories.interfaces.rest.transform;

import com.theprojectchow.backend.inventories.domain.model.commands.CreateMaterialCommand;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.CreateMaterialResource;

public class CreateMaterialCommandFromResourceAssembler {
    public static CreateMaterialCommand toCommandFromResource(CreateMaterialResource resource) {
        return new CreateMaterialCommand(resource.name(), resource.quantity(), resource.stand());
    }
}
