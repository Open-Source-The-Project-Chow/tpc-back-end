package com.theprojectchow.backend.profile.domain.model.aggregates;

import com.theprojectchow.backend.profile.domain.model.commands.CreateCraftsmanCommand;
import com.theprojectchow.backend.profile.domain.model.valueobjects.EmailAddress;
import com.theprojectchow.backend.profile.domain.model.valueobjects.PersonName;
import com.theprojectchow.backend.profile.domain.model.valueobjects.ProfileId;
import com.theprojectchow.backend.profile.domain.model.valueobjects.UserId;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Craftsman extends AuditableAbstractAggregateRoot<Craftsman> {

    @Embedded
    private Profile profile;

    @Embedded
    private UserId userId;

    public Craftsman(CreateCraftsmanCommand command, UserId userId) {
        this.profile = new Profile(command.firstName(), command.lastName(), command.email(), command.phone());
        this.userId = userId;
    }

    public Craftsman() {
        this.profile = new Profile("", "", "", "");
    }

    /*
    @Embedded
    private final ProfileId profileId;
    @Embedded
    private PersonName craftsmanName;

    @Embedded
    private EmailAddress email;

    @Getter
    private String craftsmanPhone;
    @Getter
    private String craftsmanAge;
    @Getter
    private String craftsmanNationality;
    @Getter
    private Long userId;


    public Craftsman(
            String firstName,
            String lastName,
            String email,
            String craftsmanPhone,
            String craftsmanAge,
            String craftsmanNationality,
            Long userId
    ) {
        this();
        this.craftsmanName = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.craftsmanPhone = craftsmanPhone;
        this.craftsmanAge = craftsmanAge;
        this.craftsmanNationality = craftsmanNationality;
        this.userId = userId;
    }

    public Craftsman(
            CreateCraftsmanCommand command
    ) {
        this();
        this.craftsmanName = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.craftsmanPhone=command.craftsmanPhone();
        this.craftsmanAge = command.craftsmanAge();
        this.craftsmanNationality = command.craftsmanNationality();
        this.userId = command.userId();
    }

    public Craftsman() {
        this.profileId = new ProfileId();
    }

    public void updateName(String firstName, String lastName) {
        this.craftsmanName = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updatePhone(String craftsmanPhone) {
        this.craftsmanPhone = craftsmanPhone;
    }
    public void updateAge(String craftsmanAge) {
        this.craftsmanAge = craftsmanAge;
    }
    public void updateNationality(String craftsmanNationality) {
        this.craftsmanNationality = craftsmanNationality;
    }

    public String getFullName() { return craftsmanName.getFullName(); }

    public String getEmailAddress() { return email.address(); }*/

}