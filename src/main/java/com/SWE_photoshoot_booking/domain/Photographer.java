package com.SWE_photoshoot_booking.domain;

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
@Table(name = "photographer")
public class Photographer {

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
