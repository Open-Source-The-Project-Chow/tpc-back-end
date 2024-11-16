package com.theprojectchow.backend.profile.interfaces.rest.resources;

public record UpdateProfileResource(String username,
                                    String password,
                                    String name,
                                    String surname,
                                    String email,
                                    String dni,
                                    String image) {
}
