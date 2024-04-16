package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "photographers")
public class PhotographerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long photographerID;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String telephone;

    private String workingPlace;

    private Integer yearsOfActivity;

    private String specialization;


}
