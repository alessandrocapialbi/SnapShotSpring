package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.TimeSlotService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CustomerDashboardController {


    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PhotographerDashboardController.class);

    @Autowired
    public CustomerDashboardController(UserService userService, PhotoshootService photoshootService, TimeSlotService timeSlotService) {
        this.userService = userService;
    }


    @GetMapping("/customer-dashboard")
    public String showUserDashboard(Model model, Principal principal) {
        UserEntity customer = userService.findByEmail(principal.getName());
        model.addAttribute("customerName", customer.getName());
        return "customer-dashboard";
    }
}
