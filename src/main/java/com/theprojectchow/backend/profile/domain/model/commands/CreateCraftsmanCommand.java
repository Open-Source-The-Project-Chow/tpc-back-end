package com.theprojectchow.backend.profile.domain.model.commands;

public record CreateCraftsmanCommand(
        String firstName,
        String lastName,
        String email,
        String craftsmanPhone,
        String craftsmanAge,
        String craftsmanNationality,
        Long userId
) {
}
