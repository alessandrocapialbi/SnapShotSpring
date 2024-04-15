package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.controllers.AbstractController;
import com.SWE_photoshoot_booking.domain.dto.PhotographerDto;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.PhotographerRepository;
import com.SWE_photoshoot_booking.services.impl.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photographers")
public class PhotographerController extends AbstractController<PhotographerEntity, PhotographerDto, PhotographerRepository> {


    @Autowired
    public PhotographerController(PhotographerService photographerService, Mapper<PhotographerEntity, PhotographerDto> photographerMapper) {
        super(photographerService, photographerMapper);
    }


}
