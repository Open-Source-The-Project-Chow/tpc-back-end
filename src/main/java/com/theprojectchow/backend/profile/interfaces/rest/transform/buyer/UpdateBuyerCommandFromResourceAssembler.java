package com.theprojectchow.backend.profile.interfaces.rest.transform.buyer;

import com.theprojectchow.backend.profile.domain.model.commands.UpdateBuyerCommand;
import com.theprojectchow.backend.profile.interfaces.rest.resources.buyer.UpdateBuyerResource;

public class UpdateBuyerCommandFromResourceAssembler {
    public static UpdateBuyerCommand toCommandFromResource(String id, UpdateBuyerResource resource) {
        return new UpdateBuyerCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.buyerPhone());
    }
}