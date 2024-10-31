package com.theprojectchow.backend.profile.application.internal.commandservices;

import com.theprojectchow.backend.profile.domain.model.aggregates.Profile;
import com.theprojectchow.backend.profile.domain.model.commands.ChangeProfileStatusCommand;
import com.theprojectchow.backend.profile.domain.model.commands.CreateProfileCommand;
import com.theprojectchow.backend.profile.domain.model.commands.DeleteProfileCommand;
import com.theprojectchow.backend.profile.domain.model.commands.UpdateProfileCommand;
import com.theprojectchow.backend.profile.domain.model.entities.User;
import com.theprojectchow.backend.profile.domain.services.ProfileCommandService;
import com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        var profile = new Profile(new User(command.username(),command.password(),command.name(),command.surname(), command.email(), command.dni(),command.image()),command.status());
        try {
            profileRepository.save(profile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving post"+e.getMessage());
        }
        return profile.getId();
    }
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var profile = profileRepository.findById(command.id());
        if (profile.isPresent()) {
            profile.get().updateInformation(new User(command.username(),command.password(),command.name(),command.surname(), command.email(), command.dni(),command.image()));
            return Optional.of(profileRepository.save(profile.get()));
        }
        return Optional.empty();
    }
    public void handle(DeleteProfileCommand command) {
        var profile = profileRepository.findById(command.id());
        profile.ifPresent(profileRepository::delete);
    }
    public void handle(ChangeProfileStatusCommand command) {
        var profile = profileRepository.findById(command.id());
        if (profile.isPresent()) {
            profile.get().changeStatus(command.status());
            profileRepository.save(profile.get());
        }
    }
}
