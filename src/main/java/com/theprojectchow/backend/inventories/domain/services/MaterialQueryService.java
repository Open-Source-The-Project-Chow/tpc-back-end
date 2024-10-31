package com.theprojectchow.backend.inventories.domain.services;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllMaterialsQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetMaterialByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MaterialQueryService {
    Optional<Material> handle(GetMaterialByIdQuery query);
    List<Material> handle(GetAllMaterialsQuery query);
}
