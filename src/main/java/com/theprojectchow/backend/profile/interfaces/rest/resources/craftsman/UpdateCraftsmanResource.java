package com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman;

public record UpdateCraftsmanResource(
        String firstName,
        String lastName,
        String craftsmanPhone,
        String craftsmanAge,
        String craftsmanNationality) {
}
