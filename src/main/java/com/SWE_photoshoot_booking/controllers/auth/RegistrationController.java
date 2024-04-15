package com.SWE_photoshoot_booking.controllers.auth;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register/customer")
    public CustomerEntity registerCustomer(@RequestBody CustomerEntity customer) {
        return authenticationService.registerCustomer(customer);
    }

    @PostMapping("/register/photographer")
    public PhotographerEntity registerPhotographer(@RequestBody PhotographerEntity photographer) {
        return authenticationService.registerPhotographer(photographer);
    }
}