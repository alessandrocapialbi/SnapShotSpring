package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String telephone;

    private String role;

}
