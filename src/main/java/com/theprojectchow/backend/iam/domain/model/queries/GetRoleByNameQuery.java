package com.theprojectchow.backend.iam.domain.model.queries;


import com.theprojectchow.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
