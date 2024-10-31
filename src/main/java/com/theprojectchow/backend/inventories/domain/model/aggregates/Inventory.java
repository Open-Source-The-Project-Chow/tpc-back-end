package com.theprojectchow.backend.inventories.domain.model.aggregates;

import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

    @Id
    private Long id; // Cambiado a Long para alinearse con la clase base

    private String standName;

    @OneToMany
    private List<Material> materials;

    // Constructor con parámetros
    public Inventory(Long id, String standName, List<Material> materials) {
        this.id = id;
        this.standName = standName;
        this.materials = materials;
    }

    // Constructor sin ID para casos donde se genera automáticamente
    public Inventory(String standName, List<Material> materials) {
        this.standName = standName;
        this.materials = materials;
    }
}
