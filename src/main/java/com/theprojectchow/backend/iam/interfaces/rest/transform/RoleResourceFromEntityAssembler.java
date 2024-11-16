package com.theprojectchow.backend.iam.interfaces.rest.transform;


import com.theprojectchow.backend.iam.domain.model.entities.Role;
import com.theprojectchow.backend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
