package com.theprojectchow.backend.profile.interfaces.rest.resources.buyer;

public record BuyerResource(
        Long id,
        String fullName,
        String email,
        String buyerPhone
) {
}