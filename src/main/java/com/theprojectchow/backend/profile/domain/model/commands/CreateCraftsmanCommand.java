package com.theprojectchow.backend.profile.domain.model.commands;

import java.util.List;

public record CreateCraftsmanCommand(
        String firstName,
        String lastName,
        String email,
        String phone,
        String username,
        String password,
        List<String> roles
) {
}
