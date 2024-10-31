package com.theprojectchow.backend.items.interfaces.rest.resources;

public record OrderResource(Long orderId, String craftsmanName, String distributorName, String status,String description) {
}
