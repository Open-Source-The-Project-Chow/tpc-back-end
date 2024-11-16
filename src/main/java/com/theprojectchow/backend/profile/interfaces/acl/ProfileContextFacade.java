package com.theprojectchow.backend.profile.interfaces.acl;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.commands.CreateBuyerCommand;
import com.theprojectchow.backend.profile.domain.model.commands.CreateCraftsmanCommand;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByProfileIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByProfileIdQuery;
import com.theprojectchow.backend.profile.domain.services.ProfileQueryService;
import com.theprojectchow.backend.profile.domain.services.ProfileCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileContextFacade(ProfileQueryService profileQueryService,
                                ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    public void createBuyer(
            String firstName,
            String lastName,
            String buyerEmail,
            String buyerPhone,
            Long userId
    ){
        var createBuyerCommand = new CreateBuyerCommand(firstName, lastName, buyerEmail,buyerPhone, userId);
        profileCommandService.handle(createBuyerCommand);
    }

    public void createCraftsman(
            String firstName,
            String lastName,
            String email,
            String craftsmanPhone,
            String craftsmanAge,
            String craftsmanNationality,
            Long userId
    ){
        var createCraftsmanCommand = new CreateCraftsmanCommand(firstName, lastName, email,craftsmanPhone,craftsmanAge,craftsmanNationality,userId);
        profileCommandService.handle(createCraftsmanCommand);
    }


    public Optional<Buyer> getBuyerById(Long id){
        return profileQueryService.handle(new GetBuyerByIdQuery(id));
    }

    public Optional<Buyer> getBuyerByProfileId(String profileId){
        return profileQueryService.handle(new GetBuyerByProfileIdQuery(profileId));
    }

    public Optional<Craftsman> getCraftsmanById(Long id){
        return profileQueryService.handle(new GetCraftsmanByIdQuery(id));
    }

    public Optional<Craftsman> getCraftsmanByProfileId(String profileId){
        return profileQueryService.handle(new GetCraftsmanByProfileIdQuery(profileId));
    }

}
