package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.repositories.PhotoshootRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoshootService extends AbstractCrudService<PhotoshootEntity, PhotoshootRepository> {

    @Autowired
    public PhotoshootService(PhotoshootRepository photoshootRepository) {
        super(photoshootRepository);
    }

    @Override
    public PhotoshootEntity partialUpdate(UUID id, PhotoshootEntity entity) {
        entity.setPhotoshootID(id);
        return getRepository().findById(id).map(existingPhotoshoot -> {
            Optional.ofNullable(entity.getName()).ifPresent(existingPhotoshoot::setName);
            Optional.ofNullable(entity.getPrice()).ifPresent(existingPhotoshoot::setPrice);
            return getRepository().save(existingPhotoshoot);
        }).orElseThrow(() -> new RuntimeException("Photoshoot not exists"));
    }

}