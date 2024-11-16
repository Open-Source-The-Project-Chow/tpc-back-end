package com.theprojectchow.backend.profile.domain.services;

import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetAllBuyersQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByEmailQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByProfileIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetAllCraftsmansQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByEmailQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByProfileIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {

    List<Buyer> handle(GetAllBuyersQuery query);
    Optional<Buyer> handle(GetBuyerByEmailQuery query);
    Optional<Buyer> handle(GetBuyerByIdQuery query);
    Optional<Buyer> handle(GetBuyerByProfileIdQuery query);

    List<Craftsman> handle(GetAllCraftsmansQuery query);
    Optional<Craftsman> handle(GetCraftsmanByEmailQuery query);
    Optional<Craftsman> handle(GetCraftsmanByIdQuery query);
    Optional<Craftsman> handle(GetCraftsmanByProfileIdQuery query);
}