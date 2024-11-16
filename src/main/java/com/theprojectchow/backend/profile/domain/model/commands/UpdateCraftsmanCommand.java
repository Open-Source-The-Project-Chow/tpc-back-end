package com.theprojectchow.backend.profile.domain.model.commands;

public record UpdateCraftsmanCommand(
        String id,
        String firstName,
        String lastName,
        String craftsmanPhone,
        String craftsmanAge,
        String craftsmanNationality
) {
}
