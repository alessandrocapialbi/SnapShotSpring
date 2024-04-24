package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.dto.AppointmentDto;
import com.SWE_photoshoot_booking.domain.entities.Role;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.TimeSlotService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.UUID;

@Controller
public class CustomerDashboardController {


    private final UserService userService;

    private final TimeSlotService timeSlotService;

    private final PhotoshootService photoshootService;

    private static final Logger logger = LoggerFactory.getLogger(PhotographerDashboardController.class);

    @Autowired
    public CustomerDashboardController(UserService userService, TimeSlotService timeSlotService, PhotoshootService photoshootService) {
        this.userService = userService;
        this.timeSlotService = timeSlotService;
        this.photoshootService = photoshootService;
    }


    @GetMapping("/customer-dashboard")
    public String showUserDashboard(Model model, Principal principal) {
        UserEntity customer = userService.findByEmail(principal.getName());
        model.addAttribute("customerName", customer.getName());
        return "customer-dashboard";
    }

    @GetMapping("/book-appointment")
    public String showBookAppointmentPage(Model model) {
        model.addAttribute("appointment", new AppointmentDto());
        Pageable firstPageWithTenElements = PageRequest.of(0, 10);
        model.addAttribute("photographers", userService.findAllByRole(Role.PHOTOGRAPHER, firstPageWithTenElements));

        return "book-appointment";
    }

    @GetMapping("/photographer/{photographerId}/details")
    public String showPhotographerDetails(@PathVariable UUID photographerId, Model model) {
        Pageable firstPageWithTenElements = PageRequest.of(0, 10);
        UserEntity photographer = userService.findById(photographerId).orElseThrow(() -> new RuntimeException("Photographer not found"));
        model.addAttribute("photographer", photographer);
        model.addAttribute("timeSlots", timeSlotService.findAllById(photographer.getUserID(), firstPageWithTenElements));
        model.addAttribute("photoshoots", photoshootService.findAllById(photographer.getUserID(), firstPageWithTenElements));
        return "photographer-details";
    }
}
