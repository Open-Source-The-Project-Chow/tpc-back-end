package com.theprojectchow.backend.profile.interfaces.REST.transform;

import com.theprojectchow.backend.profile.domain.model.commands.UpdateProfileCommand;
import com.theprojectchow.backend.profile.interfaces.REST.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long id, UpdateProfileResource updateProfileResource){
        return new UpdateProfileCommand(id,
                                        updateProfileResource.username(),
                                        updateProfileResource.password(),
                                        updateProfileResource.name(),
                                        updateProfileResource.surname(),
                                        updateProfileResource.email(),
                                        updateProfileResource.dni(),
                                        updateProfileResource.image());
    }
}
