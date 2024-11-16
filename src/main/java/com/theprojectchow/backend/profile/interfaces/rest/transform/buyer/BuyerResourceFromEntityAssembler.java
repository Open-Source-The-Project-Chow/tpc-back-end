package com.theprojectchow.backend.profile.interfaces.rest.transform.buyer;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.interfaces.rest.resources.buyer.BuyerResource;

public class BuyerResourceFromEntityAssembler {
    public static BuyerResource toResourceFromEntity(Buyer buyer){
        return new BuyerResource(
                buyer.getId(),
                buyer.getFullName(),
                buyer.getEmailAddress(),
                buyer.getBuyerPhone()
        );
    }
}