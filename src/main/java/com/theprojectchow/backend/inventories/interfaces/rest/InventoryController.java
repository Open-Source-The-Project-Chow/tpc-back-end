package com.theprojectchow.backend.inventories.interfaces.rest;

import com.theprojectchow.backend.inventories.application.internal.commandservices.InventoryCommandServiceImpl;
import com.theprojectchow.backend.inventories.application.internal.queryservices.InventoryQueryServiceImpl;
import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.commands.CreateInventoryCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteInventoryCommand;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllInventorysQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetInventoryByIdQuery;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryCommandServiceImpl inventoryCommandService;
    private final InventoryQueryServiceImpl inventoryQueryService;

    public InventoryController(InventoryCommandServiceImpl inventoryCommandService,
                               InventoryQueryServiceImpl inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    @PostMapping
    public ResponseEntity<String> createInventory(@Valid @RequestBody CreateInventoryCommand command) {
        inventoryCommandService.handle(command);
        return new ResponseEntity<>("Inventario creado exitosamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable String id) {
        inventoryCommandService.handle(new DeleteInventoryCommand(id));
        return new ResponseEntity<>("Inventario eliminado exitosamente", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryQueryService.handle(new GetAllInventorysQuery());
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable String id) {
        Optional<Inventory> inventory = inventoryQueryService.handle(new GetInventoryByIdQuery(id));
        return inventory.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
