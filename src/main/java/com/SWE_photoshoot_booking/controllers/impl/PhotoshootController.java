package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.controllers.AbstractController;
import com.SWE_photoshoot_booking.domain.dto.PhotoshootDto;
import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.PhotoshootRepository;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController

@RequestMapping("/photoshoots")
public class PhotoshootController extends AbstractController<PhotoshootEntity, PhotoshootDto, PhotoshootRepository> {

    private final PhotoshootService photoshootService;

    @Autowired
    public PhotoshootController(PhotoshootService photoshootService, Mapper<PhotoshootEntity, PhotoshootDto> photoshootMapper) {
        super(photoshootService, photoshootMapper);
        this.photoshootService = photoshootService;
    }

    @GetMapping("/photographer/{photographerId}")
    public Page<PhotoshootDto> listPhotoshootsByPhotographer(@PathVariable UUID photographerId, Pageable pageable) {
        Page<PhotoshootEntity> photoshoots = photoshootService.findAllById(photographerId, pageable);
        return photoshoots.map(mapper::mapTo);
    }


}