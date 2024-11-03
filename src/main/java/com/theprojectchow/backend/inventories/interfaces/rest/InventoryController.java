package com.theprojectchow.backend.inventories.interfaces.rest;

import com.theprojectchow.backend.inventories.application.internal.commandservices.InventoryCommandServiceImpl;
import com.theprojectchow.backend.inventories.application.internal.queryservices.InventoryQueryServiceImpl;
import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.commands.CreateInventoryCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteInventoryCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteMaterialCommand;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllInventorysQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetInventoryByIdQuery;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.CreateInventoryResource;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.InventoryResource;
import com.theprojectchow.backend.inventories.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.theprojectchow.backend.inventories.interfaces.rest.transform.InventoryResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value= "/api/v1/inventories", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Inventories", description = "Inventory Management Endpoints")
public class InventoryController {

    private final InventoryCommandServiceImpl inventoryCommandService;
    private final InventoryQueryServiceImpl inventoryQueryService;

    public InventoryController(InventoryCommandServiceImpl inventoryCommandService,
                               InventoryQueryServiceImpl inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResource> createInventory(@RequestBody CreateInventoryResource createInventoryResource) {
        var createInventoryCommand= CreateInventoryCommandFromResourceAssembler.toCommandFromResource(createInventoryResource);
        var inventoryId = inventoryCommandService.handle(createInventoryCommand);
        if (inventoryId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getInventoryByIdQuery = new GetInventoryByIdQuery(inventoryId);
        var inventory = inventoryQueryService.handle(getInventoryByIdQuery);
        if (inventory.isEmpty()) return ResponseEntity.badRequest().build();
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(inventory.get());
        return new ResponseEntity<>(inventoryResource, HttpStatus.CREATED);


        /*inventoryCommandService.handle(command);
        return new ResponseEntity<>("Inventario creado exitosamente", HttpStatus.CREATED);*/
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long inventoryId) {
        var deleteInventoryCommand = new DeleteInventoryCommand(inventoryId);
        inventoryCommandService.handle(deleteInventoryCommand);
        return ResponseEntity.ok("Material with given id successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<InventoryResource>> getAllInventories() {
        var getAllInventorysQuery = new GetAllInventorysQuery();
        var inventories = inventoryQueryService.handle(getAllInventorysQuery);
        var inventoryResources = inventories.stream().map(InventoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(inventoryResources);


        /*List<Inventory> inventories = inventoryQueryService.handle(new GetAllInventorysQuery());
        return new ResponseEntity<>(inventories, HttpStatus.OK);*/
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable String id) {
        Optional<Inventory> inventory = inventoryQueryService.handle(new GetInventoryByIdQuery(id));
        return inventory.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
}
