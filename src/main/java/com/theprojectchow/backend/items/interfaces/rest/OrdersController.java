package com.theprojectchow.backend.items.interfaces.rest;

import com.theprojectchow.backend.items.domain.model.commands.ConfirmOrderCommand;
import com.theprojectchow.backend.items.domain.model.commands.RejectOrderCommand;
import com.theprojectchow.backend.items.domain.model.queries.GetAllOrdersQuery;
import com.theprojectchow.backend.items.domain.model.queries.GetOrderByIdQuery;
import com.theprojectchow.backend.items.domain.services.OrderCommandService;
import com.theprojectchow.backend.items.domain.services.OrderQueryService;
import com.theprojectchow.backend.items.interfaces.rest.resources.CreateOrderResource;
import com.theprojectchow.backend.items.interfaces.rest.resources.OrderResource;
import com.theprojectchow.backend.items.interfaces.rest.transform.CreateOrderCommandFromAssembler;
import com.theprojectchow.backend.items.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import com.theprojectchow.backend.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Orders Management Endpoints")
public class OrdersController {
    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    public OrdersController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService =orderQueryService;
    }

    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource createOrderResource) {
        var createOrderCommand = CreateOrderCommandFromAssembler.toCommandFromResource(createOrderResource);
        var orderId = orderCommandService.handle(createOrderCommand);
        if(orderId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getOrderByIdQuery = new GetOrderByIdQuery(orderId);
        var order = orderQueryService.handle(getOrderByIdQuery);
        if(order.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());
        return new ResponseEntity<>(orderResource, HttpStatus.CREATED);
    }
    @PostMapping("/{orderId}/confirmations")
    public ResponseEntity<MessageResource> confirmOrder(@PathVariable Long orderId) {
        var confirmOrderCommand = new ConfirmOrderCommand(orderId);
        orderCommandService.handle(confirmOrderCommand);
        return ResponseEntity.ok(new MessageResource("Confirmed Enrollment ID: " + orderId));
    }

    @PostMapping("/{orderId}/rejections")
    public ResponseEntity<MessageResource> rejectOrder(@PathVariable Long orderId) {
        var rejectEnrollmentCommand = new RejectOrderCommand(orderId);
        orderCommandService.handle(rejectEnrollmentCommand);
        return ResponseEntity.ok(new MessageResource("Rejected Enrollment ID: " + orderId));
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long orderId) {
        var getOrderByIdQuery = new GetOrderByIdQuery(orderId);
        var order = orderQueryService.handle(getOrderByIdQuery);
        if(order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());
        return new ResponseEntity<>(orderResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        var getAllOrdersQuery = new GetAllOrdersQuery();

        var orders = orderQueryService.handle(getAllOrdersQuery);
        var orderResources = orders.stream().map(OrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(orderResources);
    }
}
