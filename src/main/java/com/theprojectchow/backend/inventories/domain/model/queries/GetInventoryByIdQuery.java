package com.theprojectchow.backend.inventories.domain.model.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// Query para obtener un inventario por su ID
@Getter
@AllArgsConstructor // Constructor que recibe el ID
@ToString // Método toString para facilitar la depuración
public class GetInventoryByIdQuery {
    private final String id; // ID del inventario que se va a consultar
}
