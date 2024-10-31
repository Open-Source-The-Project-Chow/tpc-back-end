package com.theprojectchow.backend.items.domain.model.commands;

public record CreateOrderCommand (String craftsmanName, String distributorName, String status,String description) {
}
