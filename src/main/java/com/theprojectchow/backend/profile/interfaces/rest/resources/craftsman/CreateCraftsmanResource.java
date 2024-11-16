package com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman;

public record CreateCraftsmanResource(
        String firstName,
        String lastName,
        String email,
        String craftsmanPhone,
        String craftsmanAge,
        String craftsmanNationality,
        Long userId) {
}
