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
public class PhotoshootDto implements IdentifiableDto {

    private UUID photoshootID;

    private String name;

    private Integer price;

    private UUID photographer;


    @Override
    public void setId(UUID id) {
        photoshootID = id;
    }
}