package com.theprojectchow.backend.profile.application.internal.commandservices;

import com.theprojectchow.backend.profile.application.internal.outboundservice.acl.ExternalIamService;
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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final CraftsmanRepository craftsmanRepository;
    private final ExternalIamService externalIamService;

    public ProfileCommandServiceImpl(CraftsmanRepository craftsmanRepository, ExternalIamService externalIamService) {
        this.craftsmanRepository = craftsmanRepository;
        this.externalIamService = externalIamService;
    }

    @Override
    @Transactional
    public Long handle(CreateCraftsmanCommand command) {
        if (craftsmanRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with same email already exists");
        }
        var userId = externalIamService.createUser(command.username(), command.password(), command.roles());
        if (userId.isEmpty()) {
            throw new IllegalArgumentException("Error while creating user in IAM");
        }
        var craftsman = new Craftsman(command, userId.get());
        try {
            craftsmanRepository.save(craftsman);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }

        return craftsman.getId();
    }

    /*
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
    }*/

}
