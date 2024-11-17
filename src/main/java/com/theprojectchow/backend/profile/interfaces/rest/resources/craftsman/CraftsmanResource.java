package com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman;

import java.util.Date;

public record CraftsmanResource (
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String image,
        Date createdAt,
        Date updatedAt) {
}