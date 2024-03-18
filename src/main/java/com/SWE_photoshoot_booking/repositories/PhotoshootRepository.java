package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.Photoshoot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoshootRepository extends CrudRepository<Photoshoot, Long> {
}
