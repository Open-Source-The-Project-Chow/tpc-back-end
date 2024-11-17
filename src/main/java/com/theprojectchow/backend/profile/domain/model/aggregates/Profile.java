package com.theprojectchow.backend.profile.domain.model.aggregates;

import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import jakarta.persistence.Embedded;
import java.util.Date;

@Getter
@Embeddable
@NoArgsConstructor
public class Profile {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;

    public Profile(String firstName, String lastName, String email, String phone, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }
}
