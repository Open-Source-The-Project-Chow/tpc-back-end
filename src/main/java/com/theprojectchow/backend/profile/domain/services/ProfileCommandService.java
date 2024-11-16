package com.theprojectchow.backend.profile.domain.services;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.commands.CreateBuyerCommand;
import com.theprojectchow.backend.profile.domain.model.commands.CreateCraftsmanCommand;
import com.theprojectchow.backend.profile.domain.model.commands.UpdateBuyerCommand;
import com.theprojectchow.backend.profile.domain.model.commands.UpdateCraftsmanCommand;

import java.util.Optional;

public interface ProfileCommandService {

    Long handle(CreateCraftsmanCommand command);
    /*
    Optional<Buyer> handle(UpdateBuyerCommand command);
    Optional<Craftsman> handle(UpdateCraftsmanCommand command);

    Optional<Buyer> handle(CreateBuyerCommand command);
    Optional<Craftsman> handle(CreateCraftsmanCommand command);*/
}
