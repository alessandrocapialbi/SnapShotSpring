package com.SWE_photoshoot_booking.controllers;

import org.springframework.ui.Model;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {

    private final UserRepository userRepository;

    public DashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/customer-dashboard")
    public String showUserDashboard() {
        return "customer-dashboard";
    }

    @GetMapping("/photographer-dashboard")
    public String showPhotographerDashboard(Model model, Principal principal) {
        UserEntity photographer = userRepository.findByEmail(principal.getName());
        model.addAttribute("photographerName", photographer.getName());
        return "photographer-dashboard";
    }
}