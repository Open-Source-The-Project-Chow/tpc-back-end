package com.theprojectchow.backend.items.infrastructure.persistence.jpa.repositories;

import com.theprojectchow.backend.items.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDistributorName(String distributorName);
    List<Order> findAllByCraftsmanName(String craftsmanName);
    Optional<Order> findByCraftsmanNameAndDistributorName(String craftsmanName, String distributorName);
}
