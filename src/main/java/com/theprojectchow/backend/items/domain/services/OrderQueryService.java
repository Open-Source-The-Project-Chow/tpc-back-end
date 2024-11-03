package com.theprojectchow.backend.items.domain.services;

import com.theprojectchow.backend.items.domain.model.aggregates.Order;
import com.theprojectchow.backend.items.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface OrderQueryService {
    List<Order> handle(GetAllOrdersByCraftsmanNameQuery query);
    Optional<Order> handle(GetOrderByIdQuery query);
    List<Order> handle(GetAllOrdersQuery query);
    List<Order> handle(GetAllOrdersByDistributorNameQuery query);
    Optional<Order> handle(GetOrderByCraftsmanNameAndDistributorName query);
}
