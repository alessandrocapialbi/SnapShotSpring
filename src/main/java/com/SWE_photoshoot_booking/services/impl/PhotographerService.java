package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.repositories.PhotographerRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhotographerService extends AbstractCrudService<PhotographerEntity, PhotographerRepository> {

    @Autowired
    public PhotographerService(PhotographerRepository photographerRepository) {
        super(photographerRepository);
    }

    @Override
    public PhotographerEntity partialUpdate(Long id, PhotographerEntity entity) {
        entity.setPhotographerID(id);
        return getRepository().findById(id).map(existingPhotographer -> {
            Optional.ofNullable(entity.getName()).ifPresent(existingPhotographer::setName);
            Optional.ofNullable(entity.getSurname()).ifPresent(existingPhotographer::setSurname);
            Optional.ofNullable(entity.getEmail()).ifPresent(existingPhotographer::setEmail);
            Optional.ofNullable(entity.getPassword()).ifPresent(existingPhotographer::setPassword);
            Optional.ofNullable(entity.getTelephone()).ifPresent(existingPhotographer::setTelephone);
            Optional.ofNullable(entity.getWorkingPlace()).ifPresent(existingPhotographer::setWorkingPlace);
            Optional.ofNullable(entity.getYearsOfActivity()).ifPresent(existingPhotographer::setYearsOfActivity);
            Optional.ofNullable(entity.getSpecialization()).ifPresent(existingPhotographer::setSpecialization);
            return getRepository().save(existingPhotographer);
        }).orElseThrow(() -> new RuntimeException("Photographer not exists"));
    }


}
