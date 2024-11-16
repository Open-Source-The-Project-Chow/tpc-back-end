package com.theprojectchow.backend.profile.interfaces.rest.transform.buyer;

import com.theprojectchow.backend.profile.domain.model.commands.CreateBuyerCommand;
import com.theprojectchow.backend.profile.interfaces.rest.resources.buyer.CreateBuyerResource;

public class CreateBuyerCommandFromResourceAssembler {
    public static CreateBuyerCommand toCommandFromResource(CreateBuyerResource resource){
        return new CreateBuyerCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.buyerPhone(),
                resource.userId());
    }
}