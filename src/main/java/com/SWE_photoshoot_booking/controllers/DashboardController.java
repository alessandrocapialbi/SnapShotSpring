package com.SWE_photoshoot_booking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/customer-dashboard")
    public String showUserDashboard() {
        return "customer-dashboard";
    }

    @GetMapping("/photographer-dashboard")
    public String showPhotographerDashboard() {
        return "photographer-dashboard";
    }
}