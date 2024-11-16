package com.theprojectchow.backend.profile.application.internal.commandservices;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.commands.CreateBuyerCommand;
import com.theprojectchow.backend.profile.domain.model.commands.CreateCraftsmanCommand;
import com.theprojectchow.backend.profile.domain.model.commands.UpdateBuyerCommand;
import com.theprojectchow.backend.profile.domain.model.commands.UpdateCraftsmanCommand;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import com.theprojectchow.backend.profile.domain.services.ProfileCommandService;
import com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories.BuyerRepository;
import com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories.CraftsmanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final BuyerRepository buyerRepository;
    private final CraftsmanRepository craftsmanRepository;

    public ProfileCommandServiceImpl(BuyerRepository buyerRepository, CraftsmanRepository craftsmanRepository) {
        this.buyerRepository = buyerRepository;
        this.craftsmanRepository = craftsmanRepository;
    }

    @Override
    public Optional<Buyer> handle(UpdateBuyerCommand command) {
        var buyer = buyerRepository.findByProfileId(new ProfileId(command.id()));
        if (buyer.isEmpty())return Optional.empty();
        buyer.get().updateName(command.firstName(),command.lastName());
        buyer.get().updatePhone(command.buyerPhone());
        buyerRepository.save( buyer.get());
        return  buyer;
    }

    @Override
    public Optional<Craftsman> handle(UpdateCraftsmanCommand command) {
        var craftsman = craftsmanRepository.findByProfileId(new ProfileId(command.id()));
        if (craftsman.isEmpty())return Optional.empty();
        craftsman.get().updateName(command.firstName(),command.lastName());
        craftsman.get().updatePhone(command.craftsmanPhone());
        craftsman.get().updateAge(command.craftsmanAge());
        craftsman.get().updateNationality(command.craftsmanNationality());

        craftsmanRepository.save( craftsman.get());
        return  craftsman;
    }


    @Override
    public Optional<Buyer> handle(CreateBuyerCommand command) {

        var buyer = new Buyer(command);
        buyerRepository.save(buyer);
        return Optional.of(buyer);
    }

    @Override
    public Optional<Craftsman> handle(CreateCraftsmanCommand command) {

        var craftsman = new Craftsman(command);
        craftsmanRepository.save(craftsman);
        return Optional.of(craftsman);
    }

}
