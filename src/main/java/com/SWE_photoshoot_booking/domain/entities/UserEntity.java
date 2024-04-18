package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID userID;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String telephone;

    private String role;

    @PrePersist
    public void generateUUID() {
        if (userID == null) {
            userID = UUID.randomUUID();
        }
    }

}
