package com.SWE_photoshoot_booking.domain.dto;

import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlotDto implements IdentifiableDto {

    private Long timeSlotID;

    private Integer dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String notes;

    private boolean booked;

    private PhotographerEntity photographerEntity;


    @Override
    public void setId(Long id) {
        timeSlotID = id;
    }
}