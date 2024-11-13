package com.theprojectchow.backend.inventories.domain.model.aggregates;

import com.theprojectchow.backend.inventories.domain.model.commands.CreateInventoryCommand;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
@Getter
@Entity
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

    //@Id
    //private Long id; // Cambiado a Long para alinearse con la clase base

    private String standName;

    @OneToMany(mappedBy = "inventory")
    private List<Material> materials = new ArrayList<>();

    // Constructor vacío
    /*public Inventory() {
        this.standName = "";
        this.materials = List.of();
        //this.materials = new ArrayList<>();
    }*/
    public Inventory() {
        this.standName = "";
    }

    // Constructor con parámetros
    /*public Inventory(Long id, String standName, List<Material> materials) {
        //this.id = id;
        this.standName = standName;
        this.materials = materials;
    }*/

    // Constructor sin ID para casos donde se genera automáticamente
    public Inventory(String standName, List<Material> materials) {
        this.standName = standName;
        this.materials = materials;
    }

    public Inventory(CreateInventoryCommand command){
        this();
        this.standName = command.standName();
        //this.materials = command.materials();
    }
}
