package com.theprojectchow.backend.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record ProfileId(String RecordId) {
    public ProfileId() {
        this(UUID.randomUUID().toString());
    }
}