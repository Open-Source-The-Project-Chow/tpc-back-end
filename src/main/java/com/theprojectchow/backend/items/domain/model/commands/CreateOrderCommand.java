package com.theprojectchow.backend.items.domain.model.commands;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;

public record CreateOrderCommand (Craftsman craftsman, Buyer buyer, String status, String description) {
}
