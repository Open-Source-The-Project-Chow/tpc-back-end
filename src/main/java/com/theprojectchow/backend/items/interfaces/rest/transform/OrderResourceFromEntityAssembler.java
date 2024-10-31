package com.theprojectchow.backend.items.interfaces.rest.transform;

import com.theprojectchow.backend.items.domain.model.aggregates.Order;
import com.theprojectchow.backend.items.interfaces.rest.resources.OrderResource;

public record OrderResourceFromEntityAssembler() {

    public static OrderResource toResourceFromEntity(Order entity) {
        return new OrderResource(entity.getId(), entity.getCraftsmanName(),entity.getDistributorName(), entity.getStatus(), entity.getDescription());
    }
}