package com.SWE_photoshoot_booking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "timeslot")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeslotID;

    private Integer dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String notes;

    private boolean booked;

    @ManyToOne(cascade = CascadeType.REMOVE) // Update and Delete on CASCADE
    @JoinColumn(name = "photographerID")
    private Photographer photographer;

}
