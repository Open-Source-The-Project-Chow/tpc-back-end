package com.theprojectchow.backend.inventories.domain.model.aggregates;

import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Material extends AuditableAbstractAggregateRoot<Material> {
    private String name;

    private int quantity;

    private String stand;



}
