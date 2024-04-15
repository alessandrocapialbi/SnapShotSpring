package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customerEntity")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String telephone;

}
