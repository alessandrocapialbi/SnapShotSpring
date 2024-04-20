package com.SWE_photoshoot_booking.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class CustomerDashboardController {


    @GetMapping("/customer-dashboard")
    public String showUserDashboard() {
        return "customer-dashboard";
    }
}
