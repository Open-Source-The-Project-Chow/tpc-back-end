package com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman;

public record CraftsmanResource (
        Long id,
        String fullName,
        String email,
        String craftsmanPhone,
        String craftsmanAge,
        String craftsmanNationality) {
}