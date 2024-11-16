package com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman;

public record CraftsmanResource (
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone) {
}