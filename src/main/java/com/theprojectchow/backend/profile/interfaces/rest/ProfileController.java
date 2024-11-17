package com.theprojectchow.backend.profile.interfaces.rest;

import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetAllBuyersQuery;
import com.theprojectchow.backend.profile.domain.model.queries.buyer.GetBuyerByIdQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetAllCraftsmansQuery;
import com.theprojectchow.backend.profile.domain.model.queries.craftsman.GetCraftsmanByIdQuery;
import com.theprojectchow.backend.profile.domain.services.ProfileCommandService;
import com.theprojectchow.backend.profile.domain.services.ProfileQueryService;
import com.theprojectchow.backend.profile.interfaces.rest.resources.buyer.BuyerResource;
import com.theprojectchow.backend.profile.interfaces.rest.resources.buyer.CreateBuyerResource;
import com.theprojectchow.backend.profile.interfaces.rest.resources.buyer.UpdateBuyerResource;
import com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman.CraftsmanResource;
import com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman.CreateCraftsmanResource;
import com.theprojectchow.backend.profile.interfaces.rest.resources.craftsman.UpdateCraftsmanResource;
import com.theprojectchow.backend.profile.interfaces.rest.transform.buyer.BuyerResourceFromEntityAssembler;
import com.theprojectchow.backend.profile.interfaces.rest.transform.buyer.CreateBuyerCommandFromResourceAssembler;
import com.theprojectchow.backend.profile.interfaces.rest.transform.buyer.UpdateBuyerCommandFromResourceAssembler;
import com.theprojectchow.backend.profile.interfaces.rest.transform.craftsman.CraftsmanResourceFromEntityAssembler;
import com.theprojectchow.backend.profile.interfaces.rest.transform.craftsman.CreateCraftsmanCommandFromResourceAssembler;
import com.theprojectchow.backend.profile.interfaces.rest.transform.craftsman.UpdateCraftsmanCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Operations related to profiles")
public class ProfileController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService,
                             ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @GetMapping
    ResponseEntity<List<CraftsmanResource>> getAllProfiles() {
        var craftsman = profileQueryService.findAll();
        if (craftsman.isEmpty()) return ResponseEntity.notFound().build();
        var craftsmanResponse = craftsman.get().stream()
                .map(CraftsmanResourceFromEntityAssembler::toResourceFromEntity)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(craftsmanResponse);
    }

    @PostMapping
    public ResponseEntity<CraftsmanResource> createProfile(@RequestBody CreateCraftsmanResource createCraftsmanResource){
        var createCraftsmanCommand = CreateCraftsmanCommandFromResourceAssembler.toCommandFromResource(createCraftsmanResource);
        var craftsmanId = profileCommandService.handle(createCraftsmanCommand);
        if (craftsmanId == 0L) return ResponseEntity.badRequest().build();

        var craftsman = profileQueryService.findById(craftsmanId);
        if (craftsman.isEmpty()) return ResponseEntity.badRequest().build();

        var craftsmanResource = CraftsmanResourceFromEntityAssembler.toResourceFromEntity(craftsman.get());
        return ResponseEntity.ok(craftsmanResource);
    }

    /*
    @PostMapping("/craftsman")
    public ResponseEntity<CraftsmanResource> createCraftsman(@RequestBody CreateCraftsmanResource resource) {
        var createCraftsmanCommand = CreateCraftsmanCommandFromResourceAssembler.toCommandFromResource(resource);
        var craftsman = profileCommandService.handle(createCraftsmanCommand);
        if (craftsman.isEmpty()) return ResponseEntity.badRequest().build();
        var craftsmanResource = CraftsmanResourceFromEntityAssembler.toResourceFromEntity(craftsman.get());
        return new ResponseEntity<>(craftsmanResource, HttpStatus.CREATED);
    }

    @PutMapping("/craftsman/{id}")
    public ResponseEntity<CraftsmanResource> updateCraftsmanProfile(@PathVariable String id, @RequestBody UpdateCraftsmanResource resource) {
        var updateCraftsmanCommand = UpdateCraftsmanCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedCraftsman = profileCommandService.handle(updateCraftsmanCommand);
        if (updatedCraftsman.isEmpty()) return ResponseEntity.notFound().build();
        var craftsmanResource = CraftsmanResourceFromEntityAssembler.toResourceFromEntity(updatedCraftsman.get());
        return ResponseEntity.ok(craftsmanResource);
    }*/

    @GetMapping("/{craftsmanId}")
    public ResponseEntity<CraftsmanResource> getCraftsmanById(@PathVariable Long craftsmanId) {
        var craftsman = profileQueryService.findById(craftsmanId);
        if (craftsman.isEmpty()) return ResponseEntity.notFound().build();
        var craftsmanResource = CraftsmanResourceFromEntityAssembler.toResourceFromEntity(craftsman.get());
        return ResponseEntity.ok(craftsmanResource);
    }
    /*
    @GetMapping("/craftsmans")
    public ResponseEntity<List<CraftsmanResource>> getAllCraftsmans() {
        var getAllCraftsmansQuery = new GetAllCraftsmansQuery();
        var craftsmans = profileQueryService.handle(getAllCraftsmansQuery);
        var craftsmanResources = craftsmans.stream()
                .map(CraftsmanResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(craftsmanResources);
    }


    //Buyer
    @PostMapping("/buyer")
    public ResponseEntity<BuyerResource> createBuyer(@RequestBody CreateBuyerResource resource) {
        var createBuyerCommand = CreateBuyerCommandFromResourceAssembler.toCommandFromResource(resource);
        var buyer = profileCommandService.handle(createBuyerCommand);
        if (buyer.isEmpty()) return ResponseEntity.badRequest().build();
        var buyerResource = BuyerResourceFromEntityAssembler.toResourceFromEntity(buyer.get());
        return new ResponseEntity<>(buyerResource, HttpStatus.CREATED);
    }

    @PutMapping("/buyer/{id}")
    public ResponseEntity<BuyerResource> updateBuyerProfile(@PathVariable String id, @RequestBody UpdateBuyerResource resource) {
        var updateBuyerCommand = UpdateBuyerCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedBuyer = profileCommandService.handle(updateBuyerCommand);
        if (updatedBuyer.isEmpty()) return ResponseEntity.notFound().build();
        var buyerResource = BuyerResourceFromEntityAssembler.toResourceFromEntity(updatedBuyer.get());
        return ResponseEntity.ok(buyerResource);
    }

    @GetMapping("/{buyerId}")
    public ResponseEntity<BuyerResource> getBuyerById(@PathVariable Long buyerId) {
        var getBuyerByIdQuery = new GetBuyerByIdQuery(buyerId);
        var buyer = profileQueryService.handle(getBuyerByIdQuery);
        if (buyer.isEmpty()) return ResponseEntity.notFound().build();
        var buyerResource = BuyerResourceFromEntityAssembler.toResourceFromEntity(buyer.get());
        return ResponseEntity.ok(buyerResource);
    }

    @GetMapping("/buyers")
    public ResponseEntity<List<BuyerResource>> getAllBuyers() {
        var getAllBuyersQuery = new GetAllBuyersQuery();
        var buyers = profileQueryService.handle( getAllBuyersQuery);
        var buyerResources = buyers.stream()
                .map(BuyerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(buyerResources);
    }*/


}