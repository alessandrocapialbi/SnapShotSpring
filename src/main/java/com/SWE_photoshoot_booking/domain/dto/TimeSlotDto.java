package com.SWE_photoshoot_booking.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlotDto implements IdentifiableDto {

    private UUID timeSlotID;

    private Integer dayOfWeek;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private String notes;

    private boolean booked;

    private UUID photographer;


    @Override
    public void setId(UUID id) {
        timeSlotID = id;
    }
}