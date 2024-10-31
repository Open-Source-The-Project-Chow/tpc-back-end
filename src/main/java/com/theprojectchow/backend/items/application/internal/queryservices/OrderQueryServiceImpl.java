package com.theprojectchow.backend.items.application.internal.queryservices;

import com.theprojectchow.backend.items.domain.model.aggregates.Order;
import com.theprojectchow.backend.items.domain.model.queries.*;
import com.theprojectchow.backend.items.domain.services.OrderQueryService;
import com.theprojectchow.backend.items.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;

    public OrderQueryServiceImpl(OrderRepository enrollmentRepository) {
        this.orderRepository = enrollmentRepository;
    }

    @Override
        public List<Order> handle(GetAllOrdersByCraftsmanNameQuery query) {
        return orderRepository.findAllByCraftsmanName(query.craftsmanName());
    }

    @Override
    public List<Order> handle(GetAllOrdersByDistributorNameQuery query) {
        return orderRepository.findAllByDistributorName(query.distributorName());
    }

    @Override
    public List<Order> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.orderId());
    }

    @Override
    public Optional<Order> handle(GetOrderByCraftsmanNameAndDistributorName query) {
        return orderRepository.findByCraftsmanNameAndDistributorName(query.craftsmanName(), query.distributorName());
    }
}
