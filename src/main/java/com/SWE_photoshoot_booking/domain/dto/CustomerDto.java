package com.SWE_photoshoot_booking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long customerID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String role;

}
