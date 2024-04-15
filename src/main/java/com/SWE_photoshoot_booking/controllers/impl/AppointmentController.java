package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.controllers.AbstractController;
import com.SWE_photoshoot_booking.domain.dto.AppointmentDto;
import com.SWE_photoshoot_booking.domain.entities.AppointmentEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.AppointmentRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import com.SWE_photoshoot_booking.services.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController extends AbstractController<AppointmentEntity, AppointmentDto, AppointmentRepository> {

    @Autowired
    protected AppointmentController(AppointmentService service, Mapper<AppointmentEntity, AppointmentDto> appointmentMapper) {
        super(service, appointmentMapper);
    }

}