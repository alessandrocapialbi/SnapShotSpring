package com.SWE_photoshoot_booking.domain.dto;

import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto implements IdentifiableDto {

    private Long appointmentID;

    private UserEntity customer;

    private PhotoshootEntity photoshootEntity;

    private TimeSlotEntity timeslot;

    private UserEntity photographer;

    @Override
    public void setId(Long id) {
        appointmentID = id;
    }
}