package com.theprojectchow.backend.inventories.domain.model.commands;


//public record CreateMaterialCommand(String name, int quantity, String stand){}
public class CreateMaterialCommand {
    private Long inventoryId;
    private String name;
    private int quantity;
    private String stand;

    public CreateMaterialCommand(Long inventoryId, String name, int quantity, String stand) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.quantity = quantity;
        this.stand = stand;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStand() {
        return stand;
    }
}