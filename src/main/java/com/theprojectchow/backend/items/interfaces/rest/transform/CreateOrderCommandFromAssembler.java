package com.theprojectchow.backend.items.interfaces.rest.transform;

import com.theprojectchow.backend.items.domain.model.commands.CreateOrderCommand;
import com.theprojectchow.backend.items.interfaces.rest.resources.CreateOrderResource;

public record CreateOrderCommandFromAssembler() {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        return new CreateOrderCommand(resource.craftsmanName(),resource.distributorName(),resource.status(),resource.description());
    }
}
