package com.theprojectchow.backend.profile.application.internal.queryservices;

import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetAllBuyersQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByEmailQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByProfileIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetAllCraftsmansQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByEmailQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByProfileIdQuery;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import com.theprojectchow.backend.profile.domain.model.valueobjects.UserId;
import com.theprojectchow.backend.profile.domain.services.ProfileQueryService;
import com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories.BuyerRepository;
import com.theprojectchow.backend.profile.infrastructure.persistence.jpa.repositories.CraftsmanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final CraftsmanRepository craftsmanRepository;

    public ProfileQueryServiceImpl(CraftsmanRepository craftsmanRepository) {
        this.craftsmanRepository = craftsmanRepository;
    }

    @Override
    public Optional<Craftsman> findById(Long id) {
        return craftsmanRepository.findById(id);
    }

    @Override
    public Optional<Craftsman> findByUserId(Long userId) {
        var id = new UserId(userId);
        return craftsmanRepository.findByUserId(id);
    }

    @Override
    public Optional<List<Craftsman>> findAll() {
        return Optional.of(craftsmanRepository.findAll());
    }

    /*
    @Override
    public List<Buyer> handle(GetAllBuyersQuery query) {
        return buyerRepository.findAll();
    }
    @Override
    public Optional<Buyer> handle(GetBuyerByEmailQuery query) {
        return this.buyerRepository.findByEmail(query.emailAddress());
    }
    @Override
    public Optional<Buyer> handle(GetBuyerByIdQuery query) {
        return buyerRepository.findById(query.id());
    }
    @Override
    public Optional<Buyer> handle(GetBuyerByProfileIdQuery query) {
        var buyer = new ProfileId(query.profileId());
        return buyerRepository.findByProfileId(buyer);
    }

    @Override
    public List<Craftsman> handle(GetAllCraftsmansQuery query) {
        return craftsmanRepository.findAll();
    }
    @Override
    public Optional<Craftsman> handle(GetCraftsmanByEmailQuery query) {
        return this.craftsmanRepository.findByEmail(query.emailAddress());
    }
    @Override
    public Optional<Craftsman> handle(GetCraftsmanByIdQuery query) {
        return craftsmanRepository.findById(query.id());
    }
    @Override
    public Optional<Craftsman> handle(GetCraftsmanByProfileIdQuery query) {
        var craftsman = new ProfileId(query.profileId());
        return craftsmanRepository.findByProfileId(craftsman);
    }*/
}
