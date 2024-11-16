package com.theprojectchow.backend.profile.interfaces.rest.resources.buyer;

public record CreateBuyerResource(
        String firstName,
        String lastName,
        String email,
        String buyerPhone,
        Long userId) {
}