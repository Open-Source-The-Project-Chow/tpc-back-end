package com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman;

import java.util.List;

public record CreateCraftsmanResource(
        String firstName,
        String lastName,
        String email,
        String phone,
        String image,
        String username,
        String password,
        List<String> roles
        ) {
}
