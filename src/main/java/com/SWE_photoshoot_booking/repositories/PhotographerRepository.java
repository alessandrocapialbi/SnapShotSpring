package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<PhotographerEntity, Long> {

    PhotographerEntity findByEmail(String email);
}