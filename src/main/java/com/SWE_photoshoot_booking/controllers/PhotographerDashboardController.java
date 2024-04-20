package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.repositories.PhotoshootRepository;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

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
    public String showManagePhotoshootsPage(Model model, Pageable pageable, Principal principal) {
        Page<PhotoshootEntity> photoshoots = photoshootService.findAll(pageable);
        model.addAttribute("photoshoots", photoshoots);
        UserEntity photographer = userService.findByEmail(principal.getName());
        PhotoshootEntity photoshoot = new PhotoshootEntity();
        photoshoot.setPhotographer(photographer);
        model.addAttribute("photoshoot", photoshoot);
        return "manage-photoshoots";
    }

    @PostMapping("/createPhotoshoot")
    public ResponseEntity<PhotoshootEntity> createPhotoshoot(@RequestBody PhotoshootEntity photoshoot) {
        PhotoshootEntity createdPhotoshoot = photoshootService.save(photoshoot);
        return ResponseEntity.ok(createdPhotoshoot);
    }
}