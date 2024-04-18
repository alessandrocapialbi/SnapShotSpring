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
public class UserDto implements IdentifiableDto {

    private UUID userID;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String telephone;

    private String role;

    @Override
    public void setId(UUID id) {
        userID = id;
    }

}
