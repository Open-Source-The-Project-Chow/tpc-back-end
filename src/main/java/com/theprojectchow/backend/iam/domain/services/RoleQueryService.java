package com.theprojectchow.backend.iam.domain.services;


import com.theprojectchow.backend.iam.domain.model.entities.Role;
import com.theprojectchow.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.theprojectchow.backend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
