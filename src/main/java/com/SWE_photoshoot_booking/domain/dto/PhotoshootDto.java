package com.SWE_photoshoot_booking.domain.dto;

import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoshootDto implements IdentifiableDto {

    private Long photoshootID;

    private String name;

    private Integer price;

    private UserEntity photographer;


    @Override
    public void setId(Long id) {
        photoshootID = id;
    }
}