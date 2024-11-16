package com.theprojectchow.backend.profile.interfaces.rest.transform.craftsman;

import com.theprojectchow.backend.profile.domain.model.commands.UpdateCraftsmanCommand;
import com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman.UpdateCraftsmanResource;

public class UpdateCraftsmanCommandFromResourceAssembler {
    public static UpdateCraftsmanCommand toCommandFromResource(String id, UpdateCraftsmanResource resource) {
        return new UpdateCraftsmanCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.craftsmanPhone(),
                resource.craftsmanAge(),
                resource.craftsmanNationality());
    }
}
