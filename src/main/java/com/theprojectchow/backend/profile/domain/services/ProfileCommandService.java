package com.theprojectchow.backend.profile.domain.services;

import com.theprojectchow.backend.profile.domain.model.aggregates.Profile;
import com.theprojectchow.backend.profile.domain.model.commands.ChangeProfileStatusCommand;
import com.theprojectchow.backend.profile.domain.model.commands.CreateProfileCommand;
import com.theprojectchow.backend.profile.domain.model.commands.UpdateProfileCommand;
import com.theprojectchow.backend.profile.domain.model.commands.DeleteProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
    void handle(DeleteProfileCommand command);
    void handle(ChangeProfileStatusCommand command);
}
