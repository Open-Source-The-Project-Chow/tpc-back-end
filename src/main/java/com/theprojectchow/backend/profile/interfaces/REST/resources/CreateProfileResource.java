package com.theprojectchow.backend.profile.interfaces.REST.resources;

public record CreateProfileResource(
        String username,
        String password,
        String name,
        String surname,
        String email,
        String dni,
        String image,
        String status) {
}