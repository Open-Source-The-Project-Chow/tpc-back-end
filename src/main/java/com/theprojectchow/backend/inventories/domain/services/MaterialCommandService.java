package com.theprojectchow.backend.inventories.domain.services;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import com.theprojectchow.backend.inventories.domain.model.commands.CreateMaterialCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.DeleteMaterialCommand;
import com.theprojectchow.backend.inventories.domain.model.commands.UpdateMaterialCommand;

import java.util.Optional;

public interface MaterialCommandService {

    Long handle(CreateMaterialCommand command);

    Optional<Material> handle(UpdateMaterialCommand command);

    void handle(DeleteMaterialCommand command);
}
