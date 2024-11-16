package com.theprojectchow.backend.items.domain.model.aggregates;


import com.theprojectchow.backend.items.domain.model.commands.CreateOrderCommand;
import com.theprojectchow.backend.items.domain.model.valueobjects.OrderStatus;
import com.theprojectchow.backend.profile.domain.model.aggregates.Buyer;
import com.theprojectchow.backend.profile.domain.model.aggregates.Craftsman;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Order extends AuditableAbstractAggregateRoot<Order> {

    @Getter
    @ManyToOne
    @JoinColumn(name = "craftsman_id")
    private Craftsman craftsman;

    @Getter
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    private String distributorName;
    private String craftsmanName;

    private OrderStatus status;

    private String description;

    public Order() {
    }

    public Order(Buyer buyer, Craftsman craftsman,String description) {
        this.craftsman = craftsman;
        this.buyer = buyer;
        this.description = description;
        this.status = OrderStatus.REQUESTED;
    }

    public Order(CreateOrderCommand command) {
        this.craftsman = command.craftsman();
        this.buyer = command.buyer();
        this.description =  command.description();
        this.status = OrderStatus.REQUESTED;
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void reject() {
        this.status = OrderStatus.REJECTED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }

    public String getCraftsmanName() {
        return this.craftsmanName.toLowerCase();
    }
    public String getDistributorName() {
        return this.distributorName.toLowerCase();
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }
    public String getDescription() {
        return this.description != null ? this.description.toLowerCase() : "";
    }

    public boolean isConfirmed() {
        return this.status == OrderStatus.CONFIRMED;
    }

    public boolean isRejected() { return this.status == OrderStatus.REJECTED; }

    public boolean isCanceled() { return this.status == OrderStatus.CANCELLED; }

}