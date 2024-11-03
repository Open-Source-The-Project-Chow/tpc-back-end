package com.theprojectchow.backend.profile.domain.services;

import com.theprojectchow.backend.profile.domain.model.aggregates.Profile;
import com.theprojectchow.backend.profile.domain.model.queries.GetAllProfilesQuery;
import com.theprojectchow.backend.profile.domain.model.queries.GetProfileByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.GetAllProfilesByStatusQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
    List<Profile> handle(GetAllProfilesByStatusQuery query);
}
