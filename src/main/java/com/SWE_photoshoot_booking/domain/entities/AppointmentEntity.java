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
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private UserEntity customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photoshootID")
    private PhotoshootEntity photoshootEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeslotID")
    private TimeSlotEntity timeslot;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photographerID")
    private UserEntity photographer;

}
