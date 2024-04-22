package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.dto.PhotoshootDto;
import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class PhotographerDashboardController {

    private final UserService userService;

    private final PhotoshootService photoshootService;

    private static final Logger logger = LoggerFactory.getLogger(PhotographerDashboardController.class);

    @Autowired
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
        UserEntity photographer = userService.findByEmail(principal.getName());
        Page<PhotoshootEntity> photoshoots = photoshootService.findAllById(photographer.getUserID(), pageable);
        model.addAttribute("photoshoots", photoshoots);
        PhotoshootDto photoshoot = new PhotoshootDto();
        logger.info("Photographer: {}", photographer.getUserID());
        photoshoot.setPhotographer(photographer.getUserID());
        model.addAttribute("photoshoot", photoshoot);
        return "manage-photoshoots";
    }

}