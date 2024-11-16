package com.theprojectchow.backend.profile.domain.model.commands;

public record CreateBuyerCommand(
        String firstName,
        String lastName,
        String email,
        String buyerPhone,
        Long userId
) {
}
