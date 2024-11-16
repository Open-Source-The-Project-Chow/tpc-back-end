package com.theprojectchow.backend.iam.domain.services;


import com.theprojectchow.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
