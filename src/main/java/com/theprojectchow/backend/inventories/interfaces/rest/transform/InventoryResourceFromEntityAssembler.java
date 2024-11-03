package com.theprojectchow.backend.inventories.interfaces.rest.transform;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.InventoryResource;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.MaterialResource;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryResourceFromEntityAssembler {
    /*public static InventoryResource toResourceFromEntity(Inventory entity) {
        return new InventoryResource(entity.getId(), entity.getStandName(), entity.getMaterials());
    }*/

    public static InventoryResource toResourceFromEntity(Inventory entity) {
        List<MaterialResource> materials = entity.getMaterials().stream()
                .map(MaterialResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return new InventoryResource(entity.getId(), entity.getStandName(), materials);
    }

    /*
    public static InventoryResource toResourceFromEntity(Inventory entity) {
        List<MaterialResource> materials = entity.getMaterials().stream()
                .map(InventoryResourceFromEntityAssembler::toMaterialResource)
                .collect(Collectors.toList());

        // Conversión explícita del ID de Long a String
        return new InventoryResource(
                String.valueOf(entity.getId()),  // Aquí realizamos la conversión
                entity.getStandName(),
                materials
        );
    }

    private static MaterialResource toMaterialResource(Material material) {
        return new MaterialResource(
                material.getId(), // Asumiendo que este método está disponible en la clase base
                material.getName(),
                material.getQuantity(),
                material.getStand()
        );
    }*/
}
