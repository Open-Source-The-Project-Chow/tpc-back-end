package com.theprojectchow.backend.inventories.application.internal.commandservices;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Inventory;
import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import com.theprojectchow.backend.inventories.domain.model.commands.CreateMaterialCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteMaterialCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.UpdateMaterialCommand;
import com.theprojectchow.backend.inventories.domain.services.MaterialCommandService;
import com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MaterialCommandServiceImpl implements MaterialCommandService {

    private final MaterialRepository materialRepository;
    private final InventoryRepository inventoryRepository;

    public MaterialCommandServiceImpl(MaterialRepository materialRepository, InventoryRepository inventoryRepository) {
        this.materialRepository = materialRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    @Override
    public Long handle(CreateMaterialCommand command) {
        Inventory inventory = inventoryRepository.findById(command.getInventoryId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));

        Material material = new Material(command, inventory);
        try {
            materialRepository.save(material);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving material: " + e.getMessage());
        }
        return material.getId();
    }
    /*
    @Override
    public Long handle(CreateMaterialCommand command) {
        if (materialRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Material with same name already exists");
        }
        var material = new Material(command);
        try {
            materialRepository.save(material);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving material: " + e.getMessage());
        }
        return material.getId();
    }*/

    @Override
    public Optional<Material> handle(UpdateMaterialCommand command) {
        if (materialRepository.existsByNameAndIdIsNot(command.name(), command.id()))
            throw new IllegalArgumentException("Material with same name already exists");
        var result = materialRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Material does not exist");
        var materialToUpdate = result.get();
        try {
            var updatedMaterial = materialRepository.save(materialToUpdate.updateInformation(command.name(), command.quantity(), command.stand()));
            return Optional.of(updatedMaterial);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating material: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMaterialCommand command) {
        if (!materialRepository.existsById(command.materialId())) {
            throw new IllegalArgumentException("Material does not exist");
        }
        try {
            materialRepository.deleteById(command.materialId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting material: " + e.getMessage());
        }
    }
}
