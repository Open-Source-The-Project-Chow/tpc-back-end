package com.theprojectchow.backend.profile.domain.model.aggregates;

import com.theprojectchow.backend.profile.domain.model.entities.User;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import jakarta.persistence.Embedded;
import java.util.Date;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Embedded
    private User user;
    private String status;
    private Date status_date;
    private Date registration_date;

    public Profile() {
        this(new User(),
                Strings.EMPTY);
    }

    public Profile(User user,
                   String status) {
        this.user= user;
        this.registration_date = new Date();
        this.status_date = new Date();
        this.status = status;
    }

    public Profile updateInformation(User user) {
        this.user = user;
        return this;
    }

    public void changeStatus(String status) {
        this.status = status;
        this.status_date = new Date();
    }
}
