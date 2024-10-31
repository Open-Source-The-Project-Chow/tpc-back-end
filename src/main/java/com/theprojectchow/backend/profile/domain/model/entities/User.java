package com.theprojectchow.backend.profile.domain.model.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Embeddable
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String dni;
    private String image;

    public User() {
        this(Strings.EMPTY,
                Strings.EMPTY,
                Strings.EMPTY,
                Strings.EMPTY,
                Strings.EMPTY,
                Strings.EMPTY,
                Strings.EMPTY);
    }

    public User(String username,
                String password,
                String name,
                String surname,
                String email,
                String dni,
                String image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.image = image;
    }
}
