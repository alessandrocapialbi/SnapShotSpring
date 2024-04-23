package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "timeslots")
public class TimeSlotEntity {

    @Id
    private UUID timeslotID;

    private Integer dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean booked;

    @ManyToOne
    @JoinColumn(name = "photographerID")
    private UserEntity photographer;

    @PrePersist
    public void generateUUID() {
        if (timeslotID == null) {
            timeslotID = UUID.randomUUID();
        }
    }

}
