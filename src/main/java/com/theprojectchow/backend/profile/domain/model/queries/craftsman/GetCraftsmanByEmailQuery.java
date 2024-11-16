package com.theprojectchow.backend.profile.domain.model.queries.craftsman;

import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;

public record GetCraftsmanByEmailQuery(EmailAddress emailAddress) {
}
