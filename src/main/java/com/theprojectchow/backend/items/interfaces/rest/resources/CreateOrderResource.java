package com.theprojectchow.backend.items.interfaces.rest.resources;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;

public record CreateOrderResource (Craftsman craftsman, Buyer buyer, String status, String description) {
}
