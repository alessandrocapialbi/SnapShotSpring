package com.SWE_photoshoot_booking.controllers.auth;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Object registerUser(@RequestBody CustomerEntity customer, @RequestBody PhotographerEntity photographer) {
        if (customer != null) {
            return authenticationService.registerCustomer(customer);
        } else if (photographer != null) {
            return authenticationService.registerPhotographer(photographer);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
}