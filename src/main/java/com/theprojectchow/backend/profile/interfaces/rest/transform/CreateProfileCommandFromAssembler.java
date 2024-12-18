package com.theprojectchow.backend.profile.interfaces.rest.transform;

import com.theprojectchow.backend.profile.domain.model.commands.CreateProfileCommand;
import com.theprojectchow.backend.profile.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource createProfileResource){
        return new CreateProfileCommand(createProfileResource.username(),
                                        createProfileResource.password(),
                                        createProfileResource.name(),
                                        createProfileResource.surname(),
                                        createProfileResource.email(),
                                        createProfileResource.dni(),
                                        createProfileResource.image(),
                                        createProfileResource.status());
    }
}
