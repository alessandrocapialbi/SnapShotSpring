package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.dto.PhotoshootDto;
import com.SWE_photoshoot_booking.domain.dto.TimeSlotDto;
import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.TimeSlotService;
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

    private final TimeSlotService timeSlotService;

    private static final Logger logger = LoggerFactory.getLogger(PhotographerDashboardController.class);

    @Autowired
    public PhotographerDashboardController(UserService userService, PhotoshootService photoshootService, TimeSlotService timeSlotService) {
        this.userService = userService;
        this.photoshootService = photoshootService;
        this.timeSlotService = timeSlotService;
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

    @GetMapping("/manage-timeslots")
    public String showManageTimeslotsPage(Model model, Pageable pageable, Principal principal) {
        logger.info("Showing manage timeslots page");
        UserEntity photographer = userService.findByEmail(principal.getName());
        Page<TimeSlotEntity> timeslots = timeSlotService.findAllById(photographer.getUserID(), pageable);
        model.addAttribute("timeslots", timeslots);
        TimeSlotDto timeslot = new TimeSlotDto();
        timeslot.setPhotographer(photographer.getUserID());
        timeslot.setBooked(false);
        model.addAttribute("timeslot", timeslot);
        return "manage-timeslots";
    }

}