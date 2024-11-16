package com.theprojectchow.backend.profile.domain.model.aggregates;

import com.theprojectchow.backend.profile.domain.model.commands.CreateBuyerCommand;
import com.theprojectchow.backend.profile.domain.model.valueobjects.PersonName;
import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
public class Buyer extends AuditableAbstractAggregateRoot<Buyer> {


    @Embedded
    private final ProfileId profileId;

    @NotBlank
    @Embedded
    private PersonName buyerName;

    @Embedded
    private EmailAddress email;

    @Getter
    private String buyerPhone;

    @Getter
    private Long userId;

    public Buyer(
            String firstName,
            String lastName,
            String email,
            String buyerPhone,
            Long userId
    ) {
        this();
        this.buyerName = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.buyerPhone = buyerPhone;
        this.userId = userId;
    }

    public Buyer(
            CreateBuyerCommand command
    ) {
        this();
        this.buyerName = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.buyerPhone = command.buyerPhone();
        this.userId = command.userId();
    }

    public Buyer() {
        this.profileId = new ProfileId();
    }

    public void updateName(String firstName, String lastName) {
        this.buyerName = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updatePhone(String craftsmanPhone) {
        this.buyerPhone = craftsmanPhone;
    }

    public String getFullName() { return buyerName.getFullName(); }

    public String getEmailAddress() { return email.address(); }

}
