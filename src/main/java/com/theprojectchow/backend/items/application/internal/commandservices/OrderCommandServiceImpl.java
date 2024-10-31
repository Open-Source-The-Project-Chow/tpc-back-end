package com.theprojectchow.backend.items.application.internal.commandservices;

import com.theprojectchow.backend.items.domain.model.aggregates.Order;
import com.theprojectchow.backend.items.domain.model.commands.CancelOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.ConfirmOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.CreateOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.RejectOrderCommand;
import com.theprojectchow.backend.items.domain.services.OrderCommandService;
import com.theprojectchow.backend.items.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {


    private final OrderRepository orderRepository;


    public OrderCommandServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long handle(CreateOrderCommand command) {

        var order = new Order(command);
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error craeting ordfer"+e.getMessage());
        }
        return order.getId();
    }
    @Override
    public Long handle(ConfirmOrderCommand command) {
        orderRepository.findById(command.orderId()).map(order -> {
            order.confirm();
            orderRepository.save(order);
            return command.orderId();
        }).orElseThrow(() -> new RuntimeException("Order not found"));
        return null;
    }


    @Override
    public Long handle(RejectOrderCommand command) {
        orderRepository.findById(command.orderId()).map(order -> {
            order.reject();
            orderRepository.save(order);
            return order.getId();
        }).orElseThrow(() -> new RuntimeException("Order not found"));
        return null;
    }


    @Override
    public Long handle(CancelOrderCommand command) {
        orderRepository.findById(command.orderId()).map(order -> {
            order.cancel();
            orderRepository.save(order);
            return order.getId();
        }).orElseThrow(() -> new RuntimeException("Order not found"));
        return null;
    }
}
