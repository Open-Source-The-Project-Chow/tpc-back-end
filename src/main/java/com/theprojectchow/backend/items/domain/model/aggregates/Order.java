package com.theprojectchow.backend.items.domain.model.aggregates;


import com.theprojectchow.backend.items.domain.model.commands.CreateOrderCommand;
import com.theprojectchow.backend.items.domain.model.valueobjects.OrderStatus;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;

@Entity
public class Order extends AuditableAbstractAggregateRoot<Order> {

    /*@Getter
    @ManyToOne
    @JoinColumn(name = "craftsman_id")
    private Craftsman craftsman;

    @Getter
    @ManyToOne
    @JoinColumn(name = "craftsman_id")
    private Distributor distributor;*/

    private String distributorName;
    private String craftsmanName;

    private OrderStatus status;

    private String description;

    public Order() {
    }

    public Order(String distributorName, String craftsmanName,String description) {
        this.craftsmanName = craftsmanName;
        this.distributorName = distributorName;
        this.description = description;
        this.status = OrderStatus.REQUESTED;
    }

    public Order(CreateOrderCommand command) {
        this.craftsmanName = command.craftsmanName();
        this.distributorName = command.distributorName();
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