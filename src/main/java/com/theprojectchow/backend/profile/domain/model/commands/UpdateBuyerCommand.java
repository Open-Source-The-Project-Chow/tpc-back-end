package com.theprojectchow.backend.profile.domain.model.commands;

public record UpdateBuyerCommand (
        String id,
        String firstName,
        String lastName,
        String buyerPhone
) {
}
