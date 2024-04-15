package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.domain.dto.TimeSlotDto;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.TimeSlotRepository;
import com.SWE_photoshoot_booking.services.impl.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SWE_photoshoot_booking.controllers.AbstractController;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController extends AbstractController<TimeSlotEntity, TimeSlotDto, TimeSlotRepository> {

    @Autowired
    public TimeSlotController(TimeSlotService service, Mapper<TimeSlotEntity, TimeSlotDto> timeSlotMapper) {
        super(service, timeSlotMapper);
    }

}