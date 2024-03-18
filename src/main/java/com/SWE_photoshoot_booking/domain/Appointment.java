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
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentID;

    @ManyToOne(cascade = CascadeType.ALL) // Update and Delete on CASCADE
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL) // Update and Delete on CASCADE
    @JoinColumn(name = "photoshootID")
    private Photoshoot photoshoot;

    @ManyToOne(cascade = CascadeType.ALL) // Update and Delete on CASCADE
    @JoinColumn(name = "timeslotID")
    private TimeSlot timeslot;

    @ManyToOne(cascade = CascadeType.ALL) // Update and Delete on CASCADE
    @JoinColumn(name = "photographerID")
    private Photographer photographer;

}
