package com.theprojectchow.backend.profile.domain.model.commands;

public record UpdateProfileCommand(Long id,
                                   String username,
                                   String password,
                                   String name,
                                   String surname,
                                   String email,
                                   String dni,
                                   String image) {
}
