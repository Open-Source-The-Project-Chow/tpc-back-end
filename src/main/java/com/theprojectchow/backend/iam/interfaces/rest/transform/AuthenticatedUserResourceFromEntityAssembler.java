package com.theprojectchow.backend.iam.interfaces.rest.transform;


import com.theprojectchow.backend.iam.domain.model.aggregates.User;
import com.theprojectchow.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
