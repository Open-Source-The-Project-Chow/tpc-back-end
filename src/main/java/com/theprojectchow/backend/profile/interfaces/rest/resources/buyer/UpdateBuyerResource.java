package com.theprojectchow.backend.profile.interfaces.rest.resources.buyer;

public record UpdateBuyerResource(
        String firstName,
        String lastName,
        String buyerPhone) {
}
