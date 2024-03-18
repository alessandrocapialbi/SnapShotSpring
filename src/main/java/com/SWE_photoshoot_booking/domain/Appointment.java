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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "photoshootID")
    private Photoshoot photoshoot;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "timeslotID")
    private TimeSlot timeslot;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "photographerID")
    private Photographer photographer;

}
