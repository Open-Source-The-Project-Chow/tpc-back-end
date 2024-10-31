package com.theprojectchow.backend.inventories.interfaces.rest;

import com.theprojectchow.backend.inventories.domain.model.commands.DeleteMaterialCommand;
import com.theprojectchow.backend.inventories.domain.model.queries.GetAllMaterialsQuery;
import com.theprojectchow.backend.inventories.domain.model.queries.GetMaterialByIdQuery;
import com.theprojectchow.backend.inventories.domain.services.MaterialCommandService;
import com.theprojectchow.backend.inventories.domain.services.MaterialQueryService;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.CreateMaterialResource;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.MaterialResource;
import com.theprojectchow.backend.inventories.interfaces.rest.resources.UpdateMaterialResource;
import com.theprojectchow.backend.inventories.interfaces.rest.transform.CreateMaterialCommandFromResourceAssembler;
import com.theprojectchow.backend.inventories.interfaces.rest.transform.MaterialResourceFromEntityAssembler;
import com.theprojectchow.backend.inventories.interfaces.rest.transform.UpdateMaterialCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/materials", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Materials", description = "Material Management Endpoints")
public class MaterialsController {
    private final MaterialCommandService materialCommandService;
    private final MaterialQueryService materialQueryService;

    public MaterialsController(MaterialCommandService materialCommandService, MaterialQueryService materialQueryService) {
        this.materialCommandService = materialCommandService;
        this.materialQueryService = materialQueryService;
    }

    @PostMapping
    public ResponseEntity<MaterialResource> createMaterial(@RequestBody CreateMaterialResource createMaterialResource) {
        var createMaterialCommand = CreateMaterialCommandFromResourceAssembler.toCommandFromResource(createMaterialResource);
        var materialId = materialCommandService.handle(createMaterialCommand);
        if (materialId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getMaterialByIdQuery = new GetMaterialByIdQuery(materialId);
        var material = materialQueryService.handle(getMaterialByIdQuery);
        if (material.isEmpty()) return ResponseEntity.badRequest().build();
        var materialResource = MaterialResourceFromEntityAssembler.toResourceFromEntity(material.get());
        return new ResponseEntity<>(materialResource, HttpStatus.CREATED);
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<MaterialResource> getMaterialById(@PathVariable Long materialId) {
        var getMaterialByIdQuery = new GetMaterialByIdQuery(materialId);
        var material = materialQueryService.handle(getMaterialByIdQuery);
        if (material.isEmpty()) return ResponseEntity.notFound().build();
        var materialResource = MaterialResourceFromEntityAssembler.toResourceFromEntity(material.get());
        return ResponseEntity.ok(materialResource);
    }

    @GetMapping
    public ResponseEntity<List<MaterialResource>> getAllMaterials() {
        var getAllMaterialsQuery = new GetAllMaterialsQuery();
        var materials = materialQueryService.handle(getAllMaterialsQuery);
        var materialResources = materials.stream().map(MaterialResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(materialResources);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<MaterialResource> updateMaterial(@PathVariable Long materialId, @RequestBody UpdateMaterialResource updateMaterialResource) {
        var updateMaterialCommand = UpdateMaterialCommandFromResourceAssembler.toCommandFromResource(materialId, updateMaterialResource);
        var updatedMaterial = materialCommandService.handle(updateMaterialCommand);
        if (updatedMaterial.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var materialResource = MaterialResourceFromEntityAssembler.toResourceFromEntity(updatedMaterial.get());
        return ResponseEntity.ok(materialResource);
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long materialId) {
        var deleteMaterialCommand = new DeleteMaterialCommand(materialId);
        materialCommandService.handle(deleteMaterialCommand);
        return ResponseEntity.ok("Material with given id successfully deleted");
    }
}
