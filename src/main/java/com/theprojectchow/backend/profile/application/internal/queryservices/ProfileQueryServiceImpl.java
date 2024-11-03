package com.theprojectchow.backend.profile.application.internal.queryservices;

import com.theprojectchow.backend.profile.domain.model.aggregates.Profile;
import com.theprojectchow.backend.profile.domain.model.queries.GetAllProfilesByStatusQuery;
import com.theprojectchow.backend.profile.domain.model.queries.GetAllProfilesQuery;
import com.theprojectchow.backend.profile.domain.model.queries.GetProfileByIdQuery;
import com.theprojectchow.backend.profile.domain.services.ProfileQueryService;
import com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public List<Profile> handle(GetAllProfilesByStatusQuery query) {
        return profileRepository.findByStatus(query.status());
    }
}
