package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    private UUID appointmentID;

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

    @PrePersist
    public void generateUUID() {
        if (appointmentID == null) {
            appointmentID = UUID.randomUUID();
        }
    }

}
