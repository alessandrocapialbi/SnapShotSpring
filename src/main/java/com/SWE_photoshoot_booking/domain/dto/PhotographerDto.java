package com.SWE_photoshoot_booking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotographerDto implements IdentifiableDto {

    private Long photographerID;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String telephone;

    private String workingPlace;

    private Integer yearsOfActivity;

    private String specialization;


    @Override
    public void setId(Long id) {
        photographerID = id;
    }
}
