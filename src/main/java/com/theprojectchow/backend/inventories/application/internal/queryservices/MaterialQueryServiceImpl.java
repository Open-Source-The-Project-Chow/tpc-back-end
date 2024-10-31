package com.theprojectchow.backend.inventories.application.internal.queryservices;

import com.theprojectchow.backend.inventories.domain.model.aggregates.Material;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllMaterialsQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetMaterialByIdQuery;
import com.theprojectchow.backend.inventories.domain.services.MaterialQueryService;
import com.theprojectchow.backend.inventories.infrastructure.persistence.jpa.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialQueryServiceImpl implements MaterialQueryService {

    private final MaterialRepository materialRepository;

    public MaterialQueryServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> handle(GetMaterialByIdQuery query) {
        return materialRepository.findById(query.materialId());
    }

    @Override
    public List<Material> handle(GetAllMaterialsQuery query) {
        return materialRepository.findAll();
    }
}
