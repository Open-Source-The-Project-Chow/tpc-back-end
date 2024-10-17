package com.theprojectchow.backend.inventories.domain.model.aggregates;

import com.theprojectchow.backend.inventories.domain.model.commands.CreateMaterialCommand;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Material extends AuditableAbstractAggregateRoot<Material> {
    private String name;

    private int quantity;

    private String stand;

    public Material() {
        this.name = Strings.EMPTY;
        this.quantity = 0;
        this.stand = Strings.EMPTY;
    }

    public Material(String name, int quantity, String stand) {
        this();
        this.name = name;
        this.quantity = quantity;
        this.stand = stand;
    }

    public Material(CreateMaterialCommand command) {
        this();
        this.name = command.name();
        this.quantity = command.quantity();
        this.stand = command.stand();
    }

}
