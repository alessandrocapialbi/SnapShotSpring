package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PhotographerDashboardController {

    private final UserService userService;

    private final PhotoshootService photoshootService;

    public PhotographerDashboardController(UserService userService, PhotoshootService photoshootService) {
        this.userService = userService;
        this.photoshootService = photoshootService;
    }

    @GetMapping("/photographer-dashboard")
    public String showPhotographerDashboard(Model model, Principal principal) {
        UserEntity photographer = userService.findByEmail(principal.getName());
        model.addAttribute("photographerName", photographer.getName());
        return "photographer-dashboard";
    }

    @GetMapping("/manage-photoshoots")
    public String showManagePhotoshootsPage(Model model, Pageable pageable) {
        Page<PhotoshootEntity> photoshoots = photoshootService.findAll(pageable);
        model.addAttribute("photoshoots", photoshoots);
        PhotoshootEntity photoshoot = new PhotoshootEntity();
        model.addAttribute("photoshoot", photoshoot);
        return "manage-photoshoots";
    }

}