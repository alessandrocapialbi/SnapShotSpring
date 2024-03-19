package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.Photographer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends CrudRepository<Photographer, Long> {

}
