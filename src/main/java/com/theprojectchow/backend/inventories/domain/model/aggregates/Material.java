package com.theprojectchow.backend.inventories.domain.model.aggregates;

import com.theprojectchow.backend.inventories.domain.model.commands.CreateMaterialCommand;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Material extends AuditableAbstractAggregateRoot<Material> {
    private String name;

    private int quantity;

    private String stand;

    @ManyToOne
    private Inventory inventory;

    public Material() {
        this.name = Strings.EMPTY;
        this.quantity = 0;
        this.stand = Strings.EMPTY;
    }

    public Material(String name, int quantity, String stand, Inventory inventory) {
        this();
        this.name = name;
        this.quantity = quantity;
        this.stand = stand;
        this.inventory = inventory;
    }

    public Material(CreateMaterialCommand command, Inventory inventory) {
        this();
        this.name = command.getName();
        this.quantity = command.getQuantity();
        this.stand = command.getStand();
        this.inventory = inventory;
    }

    public Material updateInformation(String name, int quantity, String stand) {
        this.name = name;
        this.quantity = quantity;
        this.stand = stand;
        return this;
    }
}
