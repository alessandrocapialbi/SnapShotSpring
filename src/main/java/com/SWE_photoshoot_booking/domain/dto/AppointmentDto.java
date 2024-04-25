package com.SWE_photoshoot_booking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto implements IdentifiableDto {

    private UUID appointmentID;

    private UUID customer;

    private UUID photoshootEntity;

    private UUID timeslot;

    private UUID photographer;

    @Override
    public void setId(UUID id) {
        appointmentID = id;
    }
}