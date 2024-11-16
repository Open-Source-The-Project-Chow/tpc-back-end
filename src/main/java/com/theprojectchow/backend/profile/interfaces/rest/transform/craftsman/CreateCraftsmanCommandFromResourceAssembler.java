package com.theprojectchow.backend.profile.interfaces.rest.transform.craftsman;

import com.theprojectchow.backend.profile.domain.model.commands.CreateCraftsmanCommand;
import com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman.CreateCraftsmanResource;

public class CreateCraftsmanCommandFromResourceAssembler {
    public static CreateCraftsmanCommand toCommandFromResource(CreateCraftsmanResource resource) {
        return new CreateCraftsmanCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.craftsmanPhone(),
                resource.craftsmanAge(),
                resource.craftsmanNationality(),
                resource.userId());
    }
}
