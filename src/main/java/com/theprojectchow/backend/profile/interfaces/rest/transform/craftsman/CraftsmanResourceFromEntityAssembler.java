package com.theprojectchow.backend.profile.interfaces.rest.transform.craftsman;

import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman.CraftsmanResource;

public class CraftsmanResourceFromEntityAssembler {
    public static CraftsmanResource toResourceFromEntity(Craftsman entity) {
        return new CraftsmanResource(
                entity.getId(),
                entity.getProfile().getFirstName(),
                entity.getProfile().getLastName(),
                entity.getProfile().getEmail(),
                entity.getProfile().getPhone());
    }
}
