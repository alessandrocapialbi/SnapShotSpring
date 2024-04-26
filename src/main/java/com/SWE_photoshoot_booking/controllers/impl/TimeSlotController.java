package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.domain.dto.TimeSlotDto;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.TimeSlotRepository;
import com.SWE_photoshoot_booking.services.impl.TimeSlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SWE_photoshoot_booking.controllers.AbstractController;

import java.util.UUID;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController extends AbstractController<TimeSlotEntity, TimeSlotDto, TimeSlotRepository> {

    private final TimeSlotService timeSlotService;

    private final static Logger logger = LoggerFactory.getLogger(TimeSlotController.class);

    @Autowired
    public TimeSlotController(TimeSlotService service, Mapper<TimeSlotEntity, TimeSlotDto> timeSlotMapper) {
        super(service, timeSlotMapper);
        timeSlotService = service;
    }


    @GetMapping("/photographer/{photographerId}")
    public Page<TimeSlotDto> listTimeSlotsByPhotographer(@PathVariable UUID photographerId, Pageable pageable) {
        logger.info("Listing time slots for photographer with id: " + photographerId);
        Page<TimeSlotEntity> timeSlots = timeSlotService.findAllById(photographerId, pageable);
        return timeSlots.map(mapper::mapTo);
    }

    @Override
    public ResponseEntity<Void> deleteRecord(@PathVariable("id") UUID id) {
        timeSlotService.checkIfBookedAppointment(id);
        timeSlotService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}