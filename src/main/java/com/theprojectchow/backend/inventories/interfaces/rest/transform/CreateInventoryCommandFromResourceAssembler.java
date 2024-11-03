package com.theprojectchow.backend.inventories.interfaces.rest.transform;

import com.theprojectchow.backend.inventories.domain.model.commands.CreateInventoryCommand;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.CreateInventoryResource;

public class CreateInventoryCommandFromResourceAssembler {
    public static CreateInventoryCommand toCommandFromResource(CreateInventoryResource resource) {
        return new CreateInventoryCommand(resource.standName());
    }
}
